package bluetooth.inuker.com.grassinvain.network.body.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/12/29.
 */
public class NewMesPetitionModel {

    public boolean visible;
    public boolean isRed;
    public List<NewMesPetitionModel> newMesPetitionModels = new ArrayList<NewMesPetitionModel>();

    public static List<NewMesPetitionModel> getNewMesPetitions(List<NewMesPetitionModel> newMesPetitionModels){
        ArrayList<NewMesPetitionModel> tempRecords = new ArrayList<>();
        if(newMesPetitionModels != null){
            tempRecords.ensureCapacity(newMesPetitionModels.size());
            for(NewMesPetitionModel newMesPetitionModel : newMesPetitionModels){
                newMesPetitionModel.visible = true;
                tempRecords.add(newMesPetitionModel);
                for(NewMesPetitionModel tempRecord : newMesPetitionModel.newMesPetitionModels){
                    tempRecord.visible = false;
                    tempRecords.add(tempRecord);
                }
            }
        }
        return tempRecords;
    }

    public NewMesPetitionModel(boolean isRed){
        this.isRed = isRed;
    }

}
