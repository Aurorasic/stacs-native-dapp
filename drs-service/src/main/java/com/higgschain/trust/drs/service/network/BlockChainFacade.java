package com.higgschain.trust.drs.service.network;

import com.alibaba.fastjson.JSONObject;
import com.higgschain.trust.drs.model.RespData;
import com.higgschain.trust.drs.service.config.ConfigListener;
import com.higgschain.trust.drs.service.config.DomainConfig;
import com.higgschain.trust.drs.service.utils.CasDecryptResponse;
import com.higgschain.trust.drs.service.utils.DrsHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuyu
 * @date 2019-11-07
 */
@Component @Slf4j public class BlockChainFacade implements ConfigListener {

    @Autowired private DrsHttpClient client;

    private String baseUrl;

    private static Function<CasDecryptResponse, RespData<?>> converter() {
        return decryptResp -> JSONObject.parseObject(decryptResp.getData().toString(), RespData.class);
    }

    public RespData<?> send(String api, Object txData) throws IOException {
        // return client.encryptPost(txData, baseUrl + api);
        return client.post(txData, baseUrl + api, converter());
    }

    public RespData<?> send(String api, Object txData, Function<CasDecryptResponse, RespData<?>> converter)
        throws IOException {
        // return client.encryptPost(txData, baseUrl + api);
        return client.post(txData, baseUrl + api, converter);
    }

    @Override public <T> void updateNotify(T config) {
        this.baseUrl = ((DomainConfig)config).getBaseUrl();
    }

    @Nonnull @Override public Predicate<Object> filter() {
        return obj -> obj instanceof DomainConfig;
    }
}
