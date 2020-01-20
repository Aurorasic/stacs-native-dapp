package io.stacs.nav.drs.api;

import io.stacs.nav.drs.api.model.ContractVO;
import io.stacs.nav.drs.api.model.PageInfo;
import io.stacs.nav.drs.api.model.RsDomain;
import io.stacs.nav.drs.api.model.TransactionVO;
import io.stacs.nav.drs.api.model.bd.BusinessDefine;
import io.stacs.nav.drs.api.model.block.BlockHeaderVO;
import io.stacs.nav.drs.api.model.block.BlockVO;
import io.stacs.nav.drs.api.model.query.*;

import java.util.List;

/**
 * @author dekuofa1995
 * @date 2019/12/2
 */
public interface IQueryService {

    Long queryCurrentHeight();

    TransactionVO queryTxById(QueryTxVO vo);

    BlockVO queryTxByHeight(Long height);

    PageInfo<TransactionVO> queryTx(QueryTxListVO vo);

    PageInfo<BlockVO> queryBlocks(QueryBlockVO vo);

    List<ContractVO> queryContracts(QueryContractVO vo);

    String queryBalance(QueryBalanceVO vo);

    String queryContract(ContractQueryRequest vo);

    BlockHeaderVO queryBlockByHeight(QueryBlockByHeightVO vo);

    /**
     * query bd info by code
     */
    BusinessDefine queryBDByCode(String bdCode);

    /**
     * query all bd info
     */
    List<BusinessDefine> queryAllBDInfo(String bdCode);

    List<RsDomain> queryAllDomains();
}
