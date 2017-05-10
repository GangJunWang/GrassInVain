package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/11.
 */

public class ProductListBody implements Serializable {

    public Long productId;

    public Long categoryId;

    public String batchNo;

    public String productCode;

    public String productName;

    public String promotionName;

    public String logoUrl;

    public String isPromotion;

    public Long price;

    public String productDesc;

    public String status;

    public List<ProductsFormat> productFormatList;
}
