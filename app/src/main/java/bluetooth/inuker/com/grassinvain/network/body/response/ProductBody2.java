package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/17.
 */

public class ProductBody2 implements Serializable {
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
    public List<ProductBodyFormartBody> productFormatList;

}
