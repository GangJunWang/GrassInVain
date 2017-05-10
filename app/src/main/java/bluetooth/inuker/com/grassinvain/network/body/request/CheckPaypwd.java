package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;

/**
 * Created by 1 on 2017/1/16.
 */

public class CheckPaypwd implements Serializable {
    public Long payPasswordId;
    public Long userId;
    public String password;
    public String securityKey;
    public String encryptionWay;
    public String unlockTime;
    public String status;

}
