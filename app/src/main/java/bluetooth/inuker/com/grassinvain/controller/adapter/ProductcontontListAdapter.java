package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.widget.MyEditText;
import bluetooth.inuker.com.grassinvain.controller.interfaces.SubmitSpeak;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrdersecondBody;

/**
 * Created by 1 on 2017/5/11.
 */

public class ProductcontontListAdapter extends SuperAdapter<AllOrdersecondBody> {


    //声明接口
    private SubmitSpeak install;

    //设置set方法，便于获取接口，否则会出现控制针异常。
    public void setInstall(SubmitSpeak install) {
        this.install = install;
    }

    private Context context;

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public ProductcontontListAdapter(Context context, List<AllOrdersecondBody> items, int layoutResId) {
        super(context, items, layoutResId);
        this.context = context;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, AllOrdersecondBody item) {
        ImageView tupian = holder.findViewById(R.id.shop_tupian);
        Picasso.with(mContext).load(item.logoUrl).placeholder(R.mipmap.chanpin).into(tupian);
        TextView title = holder.findViewById(R.id.shop_title);
        title.setText(item.productName);
        TextView guige = holder.findViewById(R.id.shop_guige);
        if ("1".equals(item.productFormatId)) {
            guige.setText("小瓶");
        }
        if ("2".equals(item.productFormatId)) {
            guige.setText("大瓶");
        }
        TextView jiage = holder.findViewById(R.id.shop_jiage);
        jiage.setText(item.price);
        TextView shuliang = holder.findViewById(R.id.shop_shuliang);
        shuliang.setText("x" + item.count);

        //重点对EditText框的监听
        final MyEditText speak_contont = holder.findViewById(R.id.speak_contont);
        speak_contont.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = speak_contont.getText().toString();
                install.setContiont(layoutPosition,s);
            }
        });
    }

}
