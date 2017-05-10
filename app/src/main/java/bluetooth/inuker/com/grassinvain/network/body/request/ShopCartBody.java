package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;
import java.util.List;

import bluetooth.inuker.com.grassinvain.network.body.response.ProductBodyFormartBody;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class ShopCartBody implements Serializable{


    // 公用
    public String shopCarId;
    public String userId;
    public String productId;
    public String formatId;
    public String count;
    public String productName;
    public String formatName;
    public String formatPrice;
    public String logoUrl;
    public String productFormatId;
    public List<ProductBodyFormartBody> productFormatList;


    //是否被选中
    private boolean isCheckout;
    //是否是操作的动作
    private boolean isModify;


    public boolean isModify() {
        return isModify;
    }

    public void setModify(boolean modify) {
        isModify = modify;
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }
}
