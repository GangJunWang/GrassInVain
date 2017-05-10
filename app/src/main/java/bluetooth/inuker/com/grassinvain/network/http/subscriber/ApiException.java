package bluetooth.inuker.com.grassinvain.network.http.subscriber;

//API异常
public class ApiException extends RuntimeException {
    public static final class ErrorType {
        public static final int PARSE_JSON = 100001;//解析json异常
        public static final int TIMEOUT = 100002;//超时【ConnectException、InterruptedIOException】
        public static final int SERVER = 100003;//服务异常【http、服务】
        public static final int IO = 100004;
        public static final int MANUAL = 100005;//【解析服务抛出的约定的异常码】

    }

    public int errorType;//错误类型
    private int code;//异常code
    private String errMessage;//异常信息


    public ApiException(int errorType, String errMessage) {
        this.errorType = errorType;
        this.code = 0;
        this.errMessage = errMessage;
    }

    public ApiException(int errorType, int code, String errMessage) {
        this.errorType = errorType;
        this.code = code;
        this.errMessage = errMessage;
    }

    public int getCode() {
        return code;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
