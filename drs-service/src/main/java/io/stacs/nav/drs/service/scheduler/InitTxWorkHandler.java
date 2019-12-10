package io.stacs.nav.drs.service.scheduler;

import com.lmax.disruptor.WorkHandler;
import io.stacs.nav.drs.service.model.TxRequestBO;
import io.stacs.nav.drs.service.service.TxReqProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suimi
 * @date 2019/8/15
 */
@Component @Slf4j public class InitTxWorkHandler implements WorkHandler<InitTxEvent> {

    @Autowired TxReqProcessService txReqProcessService;

    @Override public void onEvent(InitTxEvent event) {
        TxRequestBO bo = event.getBo();
        txReqProcessService.processTxRequest(bo);
        event.clear();
    }
}
