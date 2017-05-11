package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;

/**
 * Created by 1 on 2017/4/19.
 */

public class AllOrdersecondBody implements Serializable {

    public String amount;
    public String orderId;
    public String productFormatId;
    public String orderInfoId;
    public String productId;
    public String productName;
    public String logoUrl;
    public String price;
    public String count;
    public String createAt;
    public String modifyAt;

    public String contont;

    public String getContont() {
        return contont;
    }

    public void setContont(String contont) {
        this.contont = contont;
    }
}
