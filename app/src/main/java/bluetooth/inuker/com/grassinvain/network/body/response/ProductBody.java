package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/11.
 */

public class ProductBody implements Serializable {

    public String pageNum;
    public String pageSize;
    public String size;
    public String startRow;
    public String endRow;
    public String total;
    public String pages;
    public List<ProductListBody> list;

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



}
