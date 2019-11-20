package com.higgschain.trust.drs.model.attestation;

import com.higgschain.trust.drs.model.BaseTxVO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import static com.higgschain.trust.drs.enums.FunctionDefineEnum.SAVE_ATTESTATION;

/**
 * @author dekuofa <br>
 * @date 2019-11-01 <br>
 */
@Getter @Setter public class SaveAttestationVO extends BaseTxVO {
    /**
     * 版本
     */
    @NotBlank @Length(max = 20) private String attestationVersion;
    /**
     * 存证内容
     */
    @NotBlank @Length(max = 4096) private String attestation;
    /**
     * 备注
     */
    @Length(max = 1024) private String remark;

    @Override public String getFunctionName() {
        return SAVE_ATTESTATION.getFunctionName();
    }
}
