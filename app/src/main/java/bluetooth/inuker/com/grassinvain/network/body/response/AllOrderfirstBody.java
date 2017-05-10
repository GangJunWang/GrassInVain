package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/19.
 */

public class AllOrderfirstBody implements Serializable {
    public String amount;
    public String contactPhone;
    public String contactName;
    public String modifyAt;
    public String createAt;
    public List<AllOrdersecondBody> orderInfoList ;
    public String orderNo;
    public String userId;
    public String address;
    public String orderId;
    public String remark;
    public String status;
}
