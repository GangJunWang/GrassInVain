package bluetooth.inuker.com.grassinvain.network.http;


import bluetooth.inuker.com.grassinvain.common.util.MConstants;

/**
 * 异常信息解析
 */
public class ErrorResponse {

    public String resultCode;
    public String result;//异常result接收错误信息
    public String errorMsg;

    public boolean isSuccess() {
        if (resultCode != null) {
            return resultCode.equals(MConstants.SUCCESS_CODE);
        } else {
            return true;
        }
    }
}
