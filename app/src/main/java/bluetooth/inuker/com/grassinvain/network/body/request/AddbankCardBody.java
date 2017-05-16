package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;

/**
 * Created by 1 on 2017/5/13.
 */

public class AddbankCardBody implements Serializable {

    public Long banksId;
    public Long userId;
    public String cardHolder;
    public String cardCode;
    public String bankName;
    public String reservedPhone;
    public String status;


}
