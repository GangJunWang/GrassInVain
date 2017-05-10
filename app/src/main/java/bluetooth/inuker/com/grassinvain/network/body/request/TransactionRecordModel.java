package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/11/30.
 * 交易记录
 */
public class TransactionRecordModel implements Serializable {

    public boolean visible;
    public List<TransactionRecordModel> transactionRecordModels = new ArrayList<TransactionRecordModel>();
    public String accountDetailId;
    public String refId;
    public String userId;
    public String type;
    public String subType;
    public String beforeAmount;
    public String amount;

    public static List<TransactionRecordModel> getTransactionRecords(List<TransactionRecordModel> transactionRecordModels){
        ArrayList<TransactionRecordModel> tempRecords = new ArrayList<>();
        if(transactionRecordModels != null){
            tempRecords.ensureCapacity(transactionRecordModels.size());
            for(TransactionRecordModel transactionRecordModel : transactionRecordModels){
                transactionRecordModel.visible = true;
                tempRecords.add(transactionRecordModel);
                for(TransactionRecordModel tempRecord : transactionRecordModel.transactionRecordModels){
                    tempRecord.visible = false;
                    tempRecords.add(tempRecord);
                }
            }
        }
        return tempRecords;
    }

}
