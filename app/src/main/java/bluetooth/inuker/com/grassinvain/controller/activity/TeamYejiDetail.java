package bluetooth.inuker.com.grassinvain.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import bluetooth.inuker.com.grassinvain.R;


public class TeamYejiDetail extends AppCompatActivity {

    private RecyclerView teamDetail;
  //  private List<PersonYejiBody> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_yeji_detail);
        initData();
        initView();
    }

    private void initData() {

        /*data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());*/


    }

    private void initView() {
        // 监听Back返回键
     /*   ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        teamDetail = (RecyclerView) findViewById(R.id.team_detail);
        TeamYejiAdapter personYejiAdapter = new TeamYejiAdapter(this,data,R.layout.team_detail_activity);
        teamDetail.setLayoutManager(new LinearLayoutManager(this));
        teamDetail.setAdapter(personYejiAdapter);*/
    }
}
