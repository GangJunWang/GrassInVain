package bluetooth.inuker.com.grassinvain.network.body.request;

import java.io.Serializable;

/**
 * Created by 1 on 2017/2/4.
 */

public class AccurateSet implements Serializable {

    public Long accurateSetId;
    public Long redPacketId;
    public String gender;
    public String emotionStatus;
    public Integer haveCar;
    public Integer minAge;
    public Integer maxAge;
    public String lowestEducation;
    public String highestEducation;
    public Long minIncome;
    public Long maxIncome;
    public String occupation;
    public String position;
    public String labels;

}
