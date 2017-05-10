package bluetooth.inuker.com.grassinvain.common.model;

import java.io.Serializable;

/**
 * Created by 1 on 2017/4/11.
 */

public class OssAuthCredentials implements Serializable {


    public String securityToken;//token
    public String accessKeySecret;//	临时Secret
    public String accessKeyId;//临时id
    public String expiration;//过期时间


    public OssAuthCredentials() {

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
