package bluetooth.inuker.com.grassinvain.network.model.RequestModel;

import java.io.Serializable;

/**
 * Created by 1 on 2016/12/26.
 */
public class UserBody implements Serializable {

    public String userMobile;//	用户名
    public String userPwd;//密码
    public String confirmPwd;//确认密码
    public String captcha;//验证码
    public String op;//操作取值见:获取验证码接口
    public String type;//	类型：0
    public String captchaToken;//验证码返回token
    // 徒然草
    public String idCardUrlZ;
    public String idCardUrlF;
    public String idCardNum;
    public String pPhoneNum;
}
