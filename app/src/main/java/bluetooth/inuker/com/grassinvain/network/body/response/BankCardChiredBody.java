package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;

/**
 * Created by 1 on 2017/4/19.
 */

public class BankCardChiredBody implements Serializable {
    public String userId;
    public String cardHolder;
    public String bankName;
    public String reservedPhone;
    public String status;
    public String cardCode;
    public String banksId;
    public boolean isClick;
    public boolean isCheck;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getReservedPhone() {
        return reservedPhone;
    }

    public void setReservedPhone(String reservedPhone) {
        this.reservedPhone = reservedPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getBanksId() {
        return banksId;
    }

    public void setBanksId(String banksId) {
        this.banksId = banksId;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
