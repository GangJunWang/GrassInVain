package bluetooth.inuker.com.grassinvain.common.util;

/**
 * Created by 1 on 2016/11/16.
 */
public class MConstants {

    /**
     * 短信验证码类型
     */
    public static final String SMSCAPTCHA_OP_REGISTER = "register"; //表格显示
    public static final String SMSCAPTCHA_OP_LOGIN = "login"; //表格显示
    public static final String SMSCAPTCHA_OP_FORGET = "forget"; //表格显示
    public static final String SMSCAPTCHA_OP_BINDING = "binding"; //表格显示

    /**
     * recycler显示样式
     */
    public static final int RECYCLER_LINEAR = 0; //列表显示
    public static final int RECYCLER_GRID = 1; //表格显示
    public static final int RECYCLER_STAGGERED_GRID = 2; //瀑布流

    // 时间
    public static final int DELAYED = 300;

    // network
    public static final String SUCCESS_CODE = "0"; //接口返回:0-成，非0失败
    // public static final String BASE_URL = "http://192.168.199.165:8080/";
    // public static final String BASE_URL = "http://192.168.199.240:8080/";
    //  public static final String BASE_URL = "http://10.22.1.184:8080/";

    // public static final String BASE_URL = "http://192.168.199.165:8090/";
    //服务器地址
    public static final String BASE_URL = "http://120.55.82.96:9080/";
    public static final int HTTP_CONNECT_TIMEOUT = 20; //

    /**
     * 用户信息相关
     */

    public static final String KEY_USER_NIM_ACCOUNT = "nim_account";
    public static final String KEY_USER_NIM_TOKEN = "wytoken";
    public static final String KEY_USER_TOKEN = "token";//token
    public static final String KEY_USER = "userInfo";//用户信息
    public static final String key_IS_FRIST_START_APP = "isFristStartApp";//是否是第一次启动app 0:第一次启动， 1:非第一次
    public static final String KEY_USER_ID = "1";
    public static final String KEY_USER_STATU = "EBL";// 状态可用
    public static final String KEY_USER_NO_STATU = " DEL";// 状态不可用
    /**
     * 卡包信息相关
     */
    public static final String KEY_CARD_BANKNAME = "中石化";// 状态可用
    public static final String KEY_CARD_BANKNAME_TWE = " 石油化";// 状态不可用


    /**
     * 文件上传类型
     */
    public static final String UPLOAD_TYPE_AVATAR = "avatar";// 个人头像
    public static final String UPLOAD_TYPE_VIDEO_IMAGE = "video_image";// 个人头像


    /**
     * 拍照、选择相片返回的uri类型
     */
    public static final String MEDIAFROM_TYPE_CONTENT = "content://media";
    public static final String MEDIAFROM_TYPE_FILE = "file:///";

    /**
     * 下拉刷新header样式{黑、白}
     */
    public static final String REFRESH_HEADER_BLACK = "black";
    public static final String REFRESH_HEADER_WHITE = "white";

    /**
     * 个人信息编辑
     */
    public static final int CHANGE_SHOUHUO_ADDRES = 0x224; // 编辑个人信息

    /**
     * 头像名称
     */
    public static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int PHOTO_REQUEST_SELECT = 1005;// 选择图片
    public static final int PHOTO_REQUEST_CAMERA = 1006;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 1007;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 1008;// 裁剪
    public static final int REQUESTCODE_UPLOAD = 1009;// 文件上传
    public static final int FILE_REQUEST_SELECT = 1010;// 选择文件by类型

    public static final String UPLOAD_FILE_TYPE_PICTURE = "file_picture";//
    public static final String UPLOAD_FILE_TYPE_PICTURE_SUFFIX = ".jpg";//
    public static final String UPLOAD_FILE_TYPE_VIDEO = "file_video";//
    public static final String UPLOAD_FILE_TYPE_VIDEO_SUFFIX = ".3gp";//

}
