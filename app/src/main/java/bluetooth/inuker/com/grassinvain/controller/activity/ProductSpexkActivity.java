package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.ProductcontontListAdapter;
import bluetooth.inuker.com.grassinvain.controller.interfaces.SubmitSpeak;
import bluetooth.inuker.com.grassinvain.network.body.request.SubSpeakBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrdersecondBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class ProductSpexkActivity extends AppCompatActivity implements SubmitSpeak {

    private RecyclerView pruduct_speak_list;
    private List<AllOrdersecondBody> data = new ArrayList<>();
    private ProductcontontListAdapter productcontontListAdapter;
    private AllOrderfirstBody oeder;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_spexk);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        oeder = (AllOrderfirstBody) intent.getSerializableExtra("order");
        initData();
        initView();
    }

    private void initData() {
        data.addAll(oeder.orderInfoList);
    }

    private void initView() {
        // 返回
        LinearLayout back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 评价列表
        pruduct_speak_list = (RecyclerView) findViewById(R.id.pruduct_speak_list);
        pruduct_speak_list.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        productcontontListAdapter = new ProductcontontListAdapter(getBaseContext(), data, R.layout.product_contont_list_activity);
        pruduct_speak_list.setAdapter(productcontontListAdapter);
        productcontontListAdapter.setInstall(ProductSpexkActivity.this);

        // 提交评价
        LinearLayout speak = (LinearLayout) findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < data.size(); i++) {
                    if ("".equals(data.get(i).contont) || null == data.get(i).contont){
                        Toast.makeText(getBaseContext(),"请填写完整评价",Toast.LENGTH_SHORT).show();
                    }else {
                        SubSpeakBody subSpeakBody = new SubSpeakBody();
                        subSpeakBody.evaluateContent = data.get(i).contont;
                        userModel.getSubSpeak(subSpeakBody, new Callback<Object>() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(getBaseContext(), "提交成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            @Override
                            public void onFailure(int resultCode, String message) {
                            }
                        });
                    }

                }
            }
        });
    }

    @Override
    public void setContiont(int position, String contont) {
        data.get(position).setContont(contont);
    }

}
