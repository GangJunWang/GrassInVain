package bluetooth.inuker.com.grassinvain.network.body.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhongxin on 2016/12/29.
 * 新消息-红包
 */
public class NewMesRedModel {

    public boolean visible;
    public boolean isRed;
    public List<NewMesRedModel> newMesRedModels = new ArrayList<NewMesRedModel>();

    public static List<NewMesRedModel> getNewMesRedModel(List<NewMesRedModel> newMesRedModels){
        ArrayList<NewMesRedModel> tempRecords = new ArrayList<>();
        if(newMesRedModels != null){
            tempRecords.ensureCapacity(newMesRedModels.size());
            for(NewMesRedModel newMesRedModel : newMesRedModels){
                newMesRedModel.visible = true;
                tempRecords.add(newMesRedModel);
                for(NewMesRedModel tempRecord : newMesRedModel.newMesRedModels){
                    tempRecord.visible = false;
                    tempRecords.add(tempRecord);
                }
            }
        }
        return tempRecords;
    }

    public NewMesRedModel(boolean isRed){
        this.isRed = isRed;
    }

}
