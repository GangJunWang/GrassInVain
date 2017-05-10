package bluetooth.inuker.com.grassinvain.network.body.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/12/29.
 */
public class SystemMessageModel {

    public boolean visible;
    public boolean isRed;
    public List<SystemMessageModel> systemMessageModels = new ArrayList<SystemMessageModel>();

    public static List<SystemMessageModel> getSystemMessageModels(List<SystemMessageModel> systemMessageModels){
        ArrayList<SystemMessageModel> tempRecords = new ArrayList<>();
        if(systemMessageModels != null){
            tempRecords.ensureCapacity(systemMessageModels.size());
            for(SystemMessageModel systemMessageModel : systemMessageModels){
                systemMessageModel.visible = true;
                tempRecords.add(systemMessageModel);
                for(SystemMessageModel tempRecord : systemMessageModel.systemMessageModels){
                    tempRecord.visible = false;
                    tempRecords.add(tempRecord);
                }
            }
        }
        return tempRecords;
    }

    public SystemMessageModel(boolean isRed){
        this.isRed = isRed;
    }


}
