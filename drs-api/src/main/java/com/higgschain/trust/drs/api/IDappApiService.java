package com.higgschain.trust.drs.api;

import com.higgschain.trust.drs.exception.DappException;
import com.higgschain.trust.drs.model.SampleRequest;
import com.higgschain.trust.drs.model.SampleResult;
import com.higgschain.trust.drs.model.attestation.SaveAttestationVO;
import com.higgschain.trust.drs.model.bd.BusinessDefine;
import com.higgschain.trust.drs.model.contract.ContractCreateVO;
import com.higgschain.trust.drs.model.contract.ContractInvokeVO;
import com.higgschain.trust.drs.model.fee.FeeTxRuleConfigVO;
import com.higgschain.trust.drs.model.identity.IdentityBDManageVO;
import com.higgschain.trust.drs.model.identity.IdentitySettingVO;
import com.higgschain.trust.drs.model.identity.KYCSettingVO;
import com.higgschain.trust.drs.model.permission.AuthPermissionVO;
import com.higgschain.trust.drs.model.permission.CancelPermissionVO;
import com.higgschain.trust.drs.model.permission.RegisterPermissionVO;
import com.higgschain.trust.drs.model.policy.ModifyPolicyVO;
import com.higgschain.trust.drs.model.policy.RegisterPolicyVO;
import com.higgschain.trust.drs.model.property.SystemPropertyConfigVO;
import com.higgschain.trust.drs.model.snapshot.BuildSnapshotVO;

/**
 * @author suimi
 * @date 2019/10/30
 */
public interface IDappApiService {

    SampleResult service(SampleRequest request);

    /**
     * publish business define
     */
    void publishBD(BusinessDefine bd) throws DappException;

    /**
     * contract creation
     */
    void contractPublish(ContractCreateVO vo) throws DappException;

    /**
     * contract invoke
     */
    void contractInvoke(ContractInvokeVO vo) throws DappException;

    /**
     * register policy
     */
    void registPolicy(RegisterPolicyVO vo) throws DappException;

    /**
     * modify policy
     */
    void modifyPolicy(ModifyPolicyVO vo) throws DappException;

    /**
     * register permission
     */
    void registerPermission(RegisterPermissionVO vo) throws DappException;

    /**
     * authorize permission
     */
    void authPermission(AuthPermissionVO vo) throws DappException;

    /**
     * cancel permission
     */
    void cancelPermission(CancelPermissionVO vo) throws DappException;

    /**
     * set identity
     */
    void identitySetting(IdentitySettingVO vo) throws DappException;

    /**
     * identity bd manage
     */
    void identityManager(IdentityBDManageVO vo) throws DappException;

    /**
     * setting KYC
     */
    void settingKYC(KYCSettingVO vo) throws DappException;

    void feeTxRuleConfig(FeeTxRuleConfigVO vo) throws DappException;

    void systemPropertyConfig(SystemPropertyConfigVO vo) throws DappException;

    void buildSnapshot(BuildSnapshotVO vo) throws DappException;

    void saveAttestation(SaveAttestationVO vo) throws DappException;
}
