package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/12.
 */

public class ProductSDeatilBody implements Serializable {

    public String productId;
    public String productName;
    public String isPromotion;
    public String productDesc;
    public String status;
    public String productFormatId;
    public String formatName;
    public String formatOption;
    public String logoUrl;
    public String productFormatPrice;
    public List<ProductSpeakBody> productDetailImageList;
    public String goumaishuliang = "1";
    public String shopCarId;

    public String price;
    public String count;

    public String amount;
    public String orderId;
    public String orderInfoId;
    public String createAt;
    public String modifyAt;


}
