package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 1 on 2017/1/16.
 */

public class Recvicequanandcard implements Serializable {

    public Date outDate;
    public long amount;


    public Recvicequanandcard(){}
    public Recvicequanandcard(Date outDate) {
        this.outDate = outDate;

    }
}
