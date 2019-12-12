package io.stacs.nav.drs.service.service;

import com.alipay.sofa.ark.container.registry.PluginServiceProvider;
import com.alipay.sofa.ark.spi.model.Plugin;
import com.alipay.sofa.ark.spi.service.ArkInject;
import com.alipay.sofa.ark.spi.service.plugin.PluginManagerService;
import com.alipay.sofa.ark.spi.service.registry.RegistryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.stacs.nav.drs.api.IQueryService;
import io.stacs.nav.drs.api.exception.DappException;
import io.stacs.nav.drs.api.model.BaseTxVO;
import io.stacs.nav.drs.api.model.TransactionPO;
import io.stacs.nav.drs.api.model.TransactionVO;
import io.stacs.nav.drs.api.model.bd.BusinessDefine;
import io.stacs.nav.drs.api.model.bd.FunctionDefine;
import io.stacs.nav.drs.api.model.block.BlockHeaderVO;
import io.stacs.nav.drs.api.model.block.BlockVO;
import io.stacs.nav.drs.api.model.query.QueryBlockByHeightVO;
import io.stacs.nav.drs.api.model.query.QueryBlockVO;
import io.stacs.nav.drs.api.model.query.QueryTxListVO;
import io.stacs.nav.drs.api.model.query.QueryTxVO;
import io.stacs.nav.drs.service.constant.Constants;
import io.stacs.nav.drs.service.dao.BlockDao;
import io.stacs.nav.drs.service.dao.TransactionDao;
import io.stacs.nav.drs.service.dao.po.BlockPO;
import io.stacs.nav.drs.service.utils.BeanConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.stacs.nav.drs.api.enums.ApiConstants.TransactionApiEnum.CREATE_CONTRACT;
import static io.stacs.nav.drs.api.exception.DappError.FUNCTION_NOT_FIND_ERROR;

/**
 * @author suimi
 * @date 2019/10/30
 */
@Slf4j @Service public class QueryService implements IQueryService, InitializingBean {

    @ArkInject RegistryService registryService;

    @ArkInject PluginManagerService pluginManagerService;

    @Autowired BlockChainService bdService;
    @Autowired BlockDao blockDao;
    @Autowired TransactionDao txDao;

    @Override public String generateSignature(BaseTxVO vo) throws DappException {
        String execPolicyId;
        BusinessDefine bd = bdService.queryBDByCode(vo.getBdCode());
        if (CREATE_CONTRACT.getFunctionName().equals(vo.getFunctionName())) {
            execPolicyId = bd.getInitPolicy();
        } else {
            List<FunctionDefine> functions = bd.getFunctions();
            Optional<FunctionDefine> define = functions.stream().filter(a -> a.getName().equals(vo.getFunctionName()))
                .findFirst();
            //check function
            if (!define.isPresent()) {
                log.warn("function define is not find,functionName:{},txId:{}", vo.getFunctionName(), vo.getTxId());
                throw new DappException(FUNCTION_NOT_FIND_ERROR);
            }
            FunctionDefine fd = define.get();
            execPolicyId = fd.getExecPolicy();
        }
        vo.setExecPolicyId(execPolicyId);
        return vo.getSignValue();
    }

    public Long queryCurrentHeight() {
        return Long.valueOf(bdService.queryCurrentHeight().toString());
    }

    @Override public TransactionPO queryTxById(QueryTxVO vo) {
        return txDao.queryByTxId(vo.getTxId());
    }

    @Override public BlockVO queryTxByHeight(Long height) {
        BlockPO po = blockDao.queryByHeight(height);
        BlockVO blockVO = BeanConvertor.convertBean(po, BlockVO.class);
        blockVO.setBlockTime(po.getBlockTime().toString());
        blockVO.setMaxHeight(blockDao.getMaxHeight());
        return blockVO;
    }

    @Override public io.stacs.nav.drs.api.model.PageInfo<TransactionVO> queryTx(QueryTxListVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        io.stacs.nav.drs.api.model.PageInfo<TransactionVO> pageInfo = BeanConvertor.convertBean(
            PageInfo.of(txDao.queryTxWithCondition(vo)), io.stacs.nav.drs.api.model.PageInfo.class);
        return pageInfo;
    }

    @Override public io.stacs.nav.drs.api.model.PageInfo<BlockVO> queryBlocks(QueryBlockVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        io.stacs.nav.drs.api.model.PageInfo<BlockVO> pageInfo = BeanConvertor.convertBean(
            PageInfo.of(blockDao.queryByCond(vo)), io.stacs.nav.drs.api.model.PageInfo.class);
        return pageInfo;
    }

    @Override public BlockHeaderVO queryBlockByHeight(QueryBlockByHeightVO vo) {
        return bdService.queryBlockByHeight(vo);
    }

    @Override public BusinessDefine queryBDByCode(String bdCode) {
        return bdService.queryBDByCode(bdCode);
    }

    @Override public List<BusinessDefine> queryAllBDInfo(String bdCode) {
        return bdService.queryAllBDInfo(bdCode);
    }

    // @formatter:off
    @Override public void afterPropertiesSet() {
        Plugin plugin = pluginManagerService.getPluginByName(Constants.SERVICE_NAME);
        if (plugin == null) {
            log.warn("init plugin is fail,get plugin is null,name:{}", Constants.SERVICE_NAME);
            return;
        }
        registryService.publishService(
            IQueryService.class,
            this,
            new PluginServiceProvider(plugin));
    }
    // @formatter:on

}
