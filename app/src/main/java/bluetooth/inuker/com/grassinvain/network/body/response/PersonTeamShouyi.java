package bluetooth.inuker.com.grassinvain.network.body.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 2017/5/12.
 */

public class PersonTeamShouyi implements Serializable {

    public List<PersonTeamShouyiBody> list;
    public String subType;
    public String months;
    public String inComeMoney;
    public String createAt;
}
