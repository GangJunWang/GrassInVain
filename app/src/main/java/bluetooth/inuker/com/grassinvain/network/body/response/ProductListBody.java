package bluetooth.inuker.com.grassinvain.network.body.response;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/11.
 */

@Table(name = "product_listbody")
public class ProductListBody implements Serializable {

    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "product")
    public Long productId;

    public Long categoryId;

    public String batchNo;

    public String productCode;

    @Column(name = "productName")
    public String productName;

    public String promotionName;

    @Column(name = "logoUrl")
    public String logoUrl;

    public String isPromotion;
    @Column(name = "price")
    public Long price;

    public String productDesc;

    @Column(name = "status")
    public String status;

    public List<ProductsFormat> productFormatList;

    public ProductListBody() {}

    public ProductListBody(int id, Long productId, String productName, String logoUrl, Long price, String status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.logoUrl = logoUrl;
        this.price = price;
        this.status = status;
    }
}
