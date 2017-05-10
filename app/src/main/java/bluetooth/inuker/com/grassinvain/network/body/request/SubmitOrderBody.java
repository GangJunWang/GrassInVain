package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;
import java.util.List;

import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;

/**
 * Created by 1 on 2017/4/19.
 */

public class SubmitOrderBody implements Serializable {

    public String remark;
    public String contactName;
    public List<ProductSDeatilBody> orderInfoList ;
    public String contactPhone;
    public String address;

    // 接收的参数
    public String amount;
    public String modifyAt ;
    public String createAt ;
    public String orderNo;
    public String userId;
    public String orderId;
    public String status;
}
