package bluetooth.inuker.com.grassinvain.common.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import bluetooth.inuker.com.grassinvain.R;

/**
 * Created by 1 on 2017/3/22.
 */

public class TextUtil {

    /**
     * 验证字符是否为空
     */

    public static boolean checkEmpty(String string) {
        boolean flag = false;
        if (!"".equals(string) && null != string){
            flag = true;
        }
        return flag;
    }

    /**
     * 正则校验
     *
     * @param
     * @param str 需要校验的字符串
     * @return 验证通过返回true
     */
    public static boolean validation(String pattern, String str) {
        if (str == null)
            return false;
        return Pattern.compile(pattern).matcher(str).matches();
    }

    /**
     * 验证手机号码是否正确，手机号长度不足或未以1开头
     */
    public static boolean checkMobileNO(Context context, String mobileNo) {
        boolean flag = false;
        if (TextUtils.isEmpty(mobileNo)) {
            return flag;
        }
        try {
            flag = validation("^[1][3,4,5,7,8][0-9]{9}$", mobileNo);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证密码：数字+大小写字母，6-15位
     */
    public static boolean checkPassword(Context context, String pwd) {
        boolean flag = false;
        if (TextUtils.isEmpty(pwd)) {
            return flag;
        }
        try {
            flag = validation("^[a-z0-9_-]{6,15}$", pwd);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 检查两次输入的密码相同
     */
    public static boolean checkTwicePasswordIsEqual(Context context, String pwd1, String pwd2) {
        boolean flag = false;
        if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            ToastUtil.show(context, context.getString(R.string.password_is_not_null));
            ;
            return flag;
        }
        if (!pwd1.equals(pwd2)) {
            ToastUtil.show(context, context.getString(R.string.twice_password_is_not_equals));
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 验证短信验证码是否输入正确
     */
    public static boolean checkSmsCodeLength(Context context, String smsCode) {
        boolean flag = false;
        if (TextUtils.isEmpty(smsCode)) {
            ToastUtil.show(context, context.getString(R.string.smscode_is_not_null));
            return flag;
        }
        try {
            flag = validation("^[0-9_-]{4}$", smsCode);
        } catch (Exception e) {
            flag = false;
        }

        if (!flag) {
            ToastUtil.show(context, context.getString(R.string.smscode_length_is_wrong));
        }
        return flag;
    }


    /**
     * 验证用户昵称
     */
    public static boolean checkNickName(Context context, String nickName) {
        boolean flag = false;
        if (TextUtils.isEmpty(nickName)) {
            ToastUtil.show(context, context.getString(R.string.nickname_is_not_null));
            return flag;
        }
        try {
            flag = validation("^[a-zA-Z0-9\\u4e00-\\u9fa5]{1,12}$", nickName);
        } catch (Exception e) {
            flag = false;
        }

        if (!flag) {
            ToastUtil.show(context, context.getString(R.string.nickname_is_wrong));
        }
        return flag;
    }

    /**
     * 集合中去除重复元素
     */

    public static List<String> removeDuplicate(List<String> list)

    {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }


    /**
     * 强制键盘隐藏或弹出
     */

    public static void setdissmiss(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 比较真实完整的判断身份证号码的工具
     *
     * @param IdCard 用户输入的身份证号码
     * @return [true符合规范, false不符合规范]
     */
    public static boolean isRealIDCard(String IdCard) {
        if (IdCard != null) {
            int correct = new IdCardUtil(IdCard).isCorrect();
            if (0 == correct) {// 符合规范
                return true;
            }
        }
        return false;
    }

    /**
     * 在字符后边两位插入分隔符
     */

    public static String insertString(String string){
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.insert(string.length()-2,".");
        String s = stringBuilder.toString();
        return s;
    }

    public static String insertString2(String string){
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.insert(string.length(),"00");
        String s = stringBuilder.toString();
        return s;
    }


    public static String fen2yuan(int money) {
        BigDecimal bigMoney = new BigDecimal(String.valueOf(money));
        BigDecimal feedRate = new BigDecimal("100");
        String returnStr = String.valueOf(bigMoney.divide(feedRate).setScale(2));
        if (returnStr.indexOf(".") > 0) {
            //正则表达
            returnStr = returnStr.replaceAll("0+?$", "");//去掉后面无用的零
            returnStr = returnStr.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return returnStr;
    }
}
