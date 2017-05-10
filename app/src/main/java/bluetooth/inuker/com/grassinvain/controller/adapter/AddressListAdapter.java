package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.AddressDetailBody;

/**
 * Created by 1 on 2017/4/13.
 */

public class AddressListAdapter extends SuperAdapter<AddressDetailBody> {
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public AddressListAdapter(Context context, List<AddressDetailBody> items, int layoutResId) {
        super(context, items, layoutResId);

    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, final AddressDetailBody item) {

        TextView address_name = holder.findViewById(R.id.address_name);
        TextView address_phone = holder.findViewById(R.id.address_phone);
        TextView address_contont = holder.findViewById(R.id.address_contont);
        /**
         * 默认地址
         */
        ImageView morendizhi = holder.findViewById(R.id.imageView4);
        TextView textView11 = holder.findViewById(R.id.textView11);
        /**
         * 编辑地址
         */
        ImageView imageView6 = holder.findViewById(R.id.imageView6);
        TextView textView13 = holder.findViewById(R.id.textView13);
        /**
         * 删除地址
         */
        ImageView imageView5 = holder.findViewById(R.id.imageView5);
        TextView textView12 = holder.findViewById(R.id.textView12);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   if (chooseType != null) {
                    chooseType.deleteAddress(layoutPosition);
                }
            }
        });
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseType != null) {
                    chooseType.deleteAddress(layoutPosition);
                }
            }
        });
        /**
         * 编辑地址
         */
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseType != null) {
                    chooseType.bianjiAddress(layoutPosition);
                }
            }
        });
        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseType != null) {
                    chooseType.bianjiAddress(layoutPosition);
                }
            }
        });
        /**
         * 默认地址
         */
        morendizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseType != null) {
                    chooseType.settingmoren(layoutPosition);
                }
            }
        });
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chooseType != null) {
                    chooseType.settingmoren(layoutPosition);
                }
            }
        });
        address_name.setText(item.userName);
        address_phone.setText(item.userMobile);
        address_contont.setText(item.address + item.addressDetail);


        if (null != item.isDefault && !"".equals(item.isDefault)){

            if (1== Integer.parseInt(item.isDefault)){
                morendizhi.setImageResource(R.mipmap.xuanzhong);
            }
            if (2== Integer.parseInt(item.isDefault)){
                morendizhi.setImageResource(R.mipmap.weixuan);
            }
        }

    }


    public ChooseType chooseType;

    public void setOnItemClickListener(ChooseType chooseType) {
        this.chooseType = chooseType;
    }

    public interface ChooseType {

        void settingmoren(int positon);

        void bianjiAddress(int position);

        void deleteAddress(int position);
    }

}
