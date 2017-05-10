package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

import bluetooth.inuker.com.grassinvain.network.body.request.ShopCartBody;

/**
 * Created by 1 on 2017/4/17.
 */

public class ShopCarListBody implements Serializable {
    public String  pageNum;
    public String  pageSize;
    public String  size;
    public String  startRow;
    public String  endRow;
    public String  total;
    public String  pages;
    public List<ShopCartBody> list;
}
