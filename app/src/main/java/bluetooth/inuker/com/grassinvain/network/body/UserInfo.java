package bluetooth.inuker.com.grassinvain.network.body;

import java.io.Serializable;

import bluetooth.inuker.com.grassinvain.network.body.response.AccountBody;

/**
 * Created by 1 on 2016/11/30.
 */
public class UserInfo implements Serializable{

    public String userAddress;//用户地址
    public String approveStatus;
    public String realName;
    public String IDCardNum;//身份证号码
    public String idcardNum;//身份证号码？？
    public String sourceFrom;//用户来源[WX:微信,SSApp:商秀App]
    public long applayUserId; // 申请用户id
    public long userBusinessCooperationId;
    public boolean isFriend;
    public long businessUserId;

    public String resultCode;
    public String result;


    /**
     * 获取用户券列表
     */
     public String ticketId;
     public String ticketType;
     public String amount;
     public String count;
     public String outDate;
    //发红包 导向接收参数
 ;
    public String type;

    /**
     * 徒然草个人中心
     */
    public String idCardUrlF;
    public String userClassName;
    public String userClassId;
    public String idCardUrlZ;
    public String bankCount;
    public String buyProportion;
    public String avatarUrl;
    public String userId;
    public String password;
    public String securityKey;
    public String userName;
    public String phoneNum;
    public String idCardNum;
    public String status;
    public String parentId;
    public AccountBody account;
    public String gender;
    /**
     * 标记是否点击过银行卡的编辑按钮
     *
     */
    public boolean isClick;
    public boolean isCheck;
}
