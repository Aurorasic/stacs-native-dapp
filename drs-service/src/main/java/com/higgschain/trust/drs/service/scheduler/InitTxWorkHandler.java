package com.higgschain.trust.drs.service.scheduler;

import com.higgschain.trust.drs.service.model.TxRequestBO;
import com.higgschain.trust.drs.service.service.TxRequestService;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suimi
 * @date 2019/8/15
 */
@Component @Slf4j public class InitTxWorkHandler implements WorkHandler<InitTxEvent> {

    @Autowired TxRequestService txRequestService;

    @Override public void onEvent(InitTxEvent event) {
        TxRequestBO bo = event.getBo();
        txRequestService.processTxRequest(bo);
        event.clear();
    }
}
