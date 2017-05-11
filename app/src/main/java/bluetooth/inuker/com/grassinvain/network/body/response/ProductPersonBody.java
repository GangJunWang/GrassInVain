package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/4/12.
 */

public class ProductPersonBody implements Serializable {

    public String userId;
    public String status;
    public String productEvaluateContentId;
    public String orderInfoId;
    public String userName;
    public List<ProductPersonImageBody>productEvaluateImagesList;
    public String avatarUrl;
    public String productId;
    public String createAt;
    public String evaluateContent;
}
