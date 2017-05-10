package bluetooth.inuker.com.grassinvain.controller.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.OrderListAdapter;
import bluetooth.inuker.com.grassinvain.controller.personinformation.TheGoodsAddress;
import bluetooth.inuker.com.grassinvain.network.body.request.SubmitOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AddressDetailBody;
import bluetooth.inuker.com.grassinvain.network.body.response.MorenAddressBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

import static java.lang.Integer.parseInt;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {


    private UserModel userModel;
    private TextView huiyuan_zhuanxiang;
    private TextView shiji_price;
    private TextView xuyao_zhifu_price;
    private RecyclerView order_list;
    private TextView moren_xuanzhong_address;
    private RelativeLayout user_address;
    private LinearLayout back;
    private List<ProductSDeatilBody> data = new ArrayList<>();
    private List<ProductSDeatilBody> list = new ArrayList<>();
    private List<ProductSDeatilBody> product;
    private MorenAddressBody morenAddressBody1;
    private Dialog mCameraDialog;
    private ImageView zhifubao_src;
    private ImageView weixin_src;
    private List<AddressDetailBody> addressDetailBodies;
    private TextView moren_name;
    private TextView moren_phone;

    private int requestCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        product = (List<ProductSDeatilBody>) intent.getSerializableExtra("product");
        data.addAll(product);
        initData();
        initView();
    }

    private void initView() {
        //返回事件
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
        // 地址选择
        user_address = (RelativeLayout) findViewById(R.id.user_address);
        user_address.setOnClickListener(this);
        // 设置默认姓名
        moren_name = (TextView) findViewById(R.id.textView23);
        // 设置默认电话
        moren_phone = (TextView) findViewById(R.id.textView28);
        // 设置提交地址
        moren_xuanzhong_address = (TextView) findViewById(R.id.moren_xuanzhong_address);
        //订单列表
        order_list = (RecyclerView) findViewById(R.id.order_list);

        // 商品合计总价
        xuyao_zhifu_price = (TextView) findViewById(R.id.xuyao_zhifu_price);
        int zongjai = 0;
        for (int i = 0; i < data.size(); i++) {
            int count = parseInt(data.get(i).count);
            int price = parseInt(data.get(i).productFormatPrice);
            zongjai += (count*price);
        }
        xuyao_zhifu_price.setText(zongjai + "");
        //添加横线 表示此价格无效
        xuyao_zhifu_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //商品实际需要付款的金额
        shiji_price = (TextView) findViewById(R.id.textView16);
        int shijiprice = 0;
        shijiprice = (int) (zongjai * 0.85);
        shiji_price.setText(shijiprice + "");
        // 提交订单
        TextView submitOrder = (TextView) findViewById(R.id.textView15);
        submitOrder.setOnClickListener(this);
        // 会员专享打折优惠
        huiyuan_zhuanxiang = (TextView) findViewById(R.id.textView18);
        order_list.setLayoutManager(new LinearLayoutManager(this));
        OrderListAdapter orderListAdapter = new OrderListAdapter(OrderActivity.this, data, R.layout.order_list_contont);
        order_list.setAdapter(orderListAdapter);

    }

    private void initData() {
        /**
         * 获取默认地址列表
         */
        userModel.getMorenAddress(new Callback<MorenAddressBody>() {
            @Override
            public void onSuccess(MorenAddressBody morenAddressBody) {
                morenAddressBody1 = morenAddressBody;
                setuserImformation(morenAddressBody1);
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }
    private void setuserImformation(MorenAddressBody morenAddressBody) {

        moren_xuanzhong_address.setText(morenAddressBody.address+morenAddressBody.addressDetail);
        moren_name.setText(morenAddressBody.userName);
        moren_phone.setText(morenAddressBody.userMobile);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.user_address:
                Intent intent =new Intent(OrderActivity.this, TheGoodsAddress.class);
               startActivityForResult(intent,requestCode);
                break;
            case R.id.textView15:
                popupWindow();


                break;
            case R.id.zhifubao:
                zhifubao_src.setImageResource(R.mipmap.xuanzhong);
                weixin_src.setImageResource(R.mipmap.weixuan);
                break;
            case R.id.weixin:
                zhifubao_src.setImageResource(R.mipmap.weixuan);
                weixin_src.setImageResource(R.mipmap.xuanzhong);
                break;
            case R.id.queren:
                 // 提交订单
                SubmitOrderBody submitOrderBody = new SubmitOrderBody();
                submitOrderBody.contactPhone = morenAddressBody1.userMobile;
                submitOrderBody.address = morenAddressBody1.address + morenAddressBody1.addressDetail;
                submitOrderBody.contactName = morenAddressBody1.userName;

                for (int i = 0; i < data.size(); i++) {

                    ProductSDeatilBody productSDeatilBody = new ProductSDeatilBody();
                    productSDeatilBody.price = data.get(i).productFormatPrice;
                    productSDeatilBody.count = data.get(i).goumaishuliang;
                    productSDeatilBody.logoUrl = data.get(i).logoUrl;
                    productSDeatilBody.productId = data.get(i).productId;
                    productSDeatilBody.productName = data.get(i).productName;
                    productSDeatilBody.formatName = data.get(i).formatName;
                    list.add(productSDeatilBody);
                }
                submitOrderBody.orderInfoList = list;
                userModel.getSubmitOrder(submitOrderBody, new Callback<SubmitOrderBody>() {
                    @Override
                    public void onSuccess(SubmitOrderBody submitOrderBody) {
                        String orderNo = submitOrderBody.orderNo;
                        perform(orderNo);
                    }
                    @Override
                    public void onFailure(int resultCode, String message) {
                    }
                });
                break;
        }
    }

    private void perform(String orderNo) {
        userModel.getSubmitOrdernumber(orderNo, new Callback<Object>() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getBaseContext(),"支付成功",Toast.LENGTH_SHORT).show();
                mCameraDialog.cancel();
            }
            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }
    private void popupWindow() {
        mCameraDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_paytype_control, null);
        root.findViewById(R.id.zhifubao).setOnClickListener(OrderActivity.this);
        root.findViewById(R.id.weixin).setOnClickListener(OrderActivity.this);
        root.findViewById(R.id.queren).setOnClickListener(OrderActivity.this);
        // 切换图片
        zhifubao_src = (ImageView) root.findViewById(R.id.zhifubao_src);
        weixin_src = (ImageView) root.findViewById(R.id.weixin_src);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // lp.x = 0; // 新位置X坐标
        // lp.y = -20; // 新位置Y坐标
        // lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        //lp.height = 300; // 高度
        // lp.alpha = 9f; // 透明度
        // root.measure(0, 0);
        lp.height = 600;
        // lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case RESULT_OK:
                Bundle extras = data.getExtras();
                AddressDetailBody address = (AddressDetailBody) extras.getSerializable("address");
                moren_name.setText(address.userName);
                moren_phone.setText(address.userMobile);
                moren_xuanzhong_address.setText(address.address+address.addressDetail);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
