package bluetooth.inuker.com.grassinvain.controller.personinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaojun.widget.superAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.activity.AddNewAddressActivity;
import bluetooth.inuker.com.grassinvain.controller.activity.ChangeAddressimformation;
import bluetooth.inuker.com.grassinvain.controller.activity.OrderActivity;
import bluetooth.inuker.com.grassinvain.controller.adapter.AddressListAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.AddressDetailBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.UserAddressListBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class TheGoodsAddress extends AppCompatActivity implements View.OnClickListener {

    private UserModel userModel;
    private ImageView back;
    private RecyclerView user_address_recycView;
    private TextView add_address;
    private List<AddressDetailBody> data = new ArrayList<>();
    private AddressListAdapter addressListAdapter;
    private boolean isOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_goods_address);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        isOrder = intent.getBooleanExtra("isOrder", false);
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
    }

    private void initView() {
        // 返回
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 地址列表
        user_address_recycView = (RecyclerView) findViewById(R.id.user_address_recycView);
        user_address_recycView.setOnClickListener(this);
        user_address_recycView.setLayoutManager(new LinearLayoutManager(this));
        //适配器准备
        addressListAdapter = new AddressListAdapter(TheGoodsAddress.this, data, R.layout.user_address_list_contont);
        user_address_recycView.setAdapter(addressListAdapter);
        // 添加地址
        add_address = (TextView) findViewById(R.id.add_address);
        add_address.setOnClickListener(this);
        addressListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Intent intent = new Intent(TheGoodsAddress.this, OrderActivity.class);
                intent.putExtra("address", data.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        addressListAdapter.setOnItemClickListener(new AddressListAdapter.ChooseType() {
            @Override
            public void settingmoren(final int positon) {
                /**
                 * 设置默认地址
                 */
                AddressDetailBody addressDetailBody = new AddressDetailBody();
                addressDetailBody.userAddressId = data.get(positon).userAddressId;
                addressDetailBody.isDefault = "1";
                userModel.getSetMorenAdress(addressDetailBody, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getBaseContext(), "设置成功", Toast.LENGTH_SHORT).show();
                      /*  for (int i = 0; i <data.size(); i++) {
                            if (positon==i){
                                data.get(i).isDefault="1";
                            }else {
                                data.get(i).isDefault="2";
                            }
                        }
                        addressListAdapter.notifyDataSetChanged();*/
                        initData();
                    }

                    @Override
                    public void onFailure(int resultCode, String message) {
                        Toast.makeText(getBaseContext(), "设置失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void bianjiAddress(int position) {
                Intent intent1 = new Intent(TheGoodsAddress.this, ChangeAddressimformation.class);
                intent1.putExtra("address_name", data.get(position).userName);
                intent1.putExtra("address_phone", data.get(position).userMobile);
                intent1.putExtra("address_privince", data.get(position).address);
                intent1.putExtra("address_detail", data.get(position).addressDetail);
                intent1.putExtra("address_Id", data.get(position).userAddressId);
                startActivity(intent1);
            }

            @Override
            public void deleteAddress(int position) {

                userModel.getDeleteAddress(data.get(position).userAddressId, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(TheGoodsAddress.this, "删除成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }

                    @Override
                    public void onFailure(int resultCode, String message) {
                        Toast.makeText(TheGoodsAddress.this, "删除失败，网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void initData() {

        PageBody pageBody = new PageBody();
        pageBody.pageSize = 20;
        pageBody.pageNum = 1;

        userModel.getAddressList(pageBody, new Callback<UserAddressListBody>() {
            @Override
            public void onSuccess(UserAddressListBody userAddressListBody) {
                setAddreesImformation(userAddressListBody.list);
            }

            @Override
            public void onFailure(int resultCode, String message) {
                Toast.makeText(getBaseContext(), "网络连接有误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAddreesImformation(List<AddressDetailBody> list) {
        data.clear();
        data.addAll(list);
        addressListAdapter.clear();
        addressListAdapter.addAll(list);
        addressListAdapter.notifyDataSetHasChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_address: {
                Intent intent1 = new Intent(TheGoodsAddress.this, AddNewAddressActivity.class);
                startActivity(intent1);
            }
            break;
        }
    }


}
