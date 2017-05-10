package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Super-me on 2017/1/3.
 */

public class UserDetailsInformation implements Serializable {
    public int userId;
    public String completeProgress;
    public String avatarUrl;
    public String userType;
    public String nickName;

    public String weiXin;
    public String phoneNum;
    public String email;
    public String address;
    public String education;
    public String gender;
    public String birthday;
    public String emotionState;

    public String income;
    public String industry;
    public String profession;
    public String position;
    public String carBrand;
    public String carTime;
    public String carPrice;
    public String carOil;
    public List<String> tag;
}
