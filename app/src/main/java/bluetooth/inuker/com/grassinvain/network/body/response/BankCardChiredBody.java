package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;

/**
 * Created by 1 on 2017/4/19.
 */

public class BankCardChiredBody implements Serializable {
    public String userId;
    public String cardHolder;
    public String bankName;
    public String reservedPhone;
    public String status;
    public String cardCode;
    public String banksId;
    public boolean isClick;
    public boolean isCheck;
}
