package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.interfaces.ChoseBank;
import bluetooth.inuker.com.grassinvain.network.body.response.BankCardChiredBody;

/**
 * Created by 1 on 2017/4/18.
 */

public class BankCardAdapter extends SuperAdapter<BankCardChiredBody> {

    private ImageView bianji_bankcard;

    /**
     * 声明接口
     */
    private ChoseBank choseBank;
    public void setbankchange(ChoseBank choseBank) {
        this.choseBank = choseBank;
    }
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public BankCardAdapter(Context context, List<BankCardChiredBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, final BankCardChiredBody item) {

        TextView bank_number = holder.findViewById(R.id.textView20);
        bank_number.setText(item.cardCode);
        TextView bank_name = holder.findViewById(R.id.textView19);
        bank_name.setText(item.bankName);
        CardView bank_cardView = holder.findViewById(R.id.bank_cardView);
        bank_cardView.setRadius(18);//设置图片圆角的半径大小
        bank_cardView.setCardElevation(18);//设置阴影部分大小
        bank_cardView.setContentPadding(15, 15, 15, 15);//设置图片距离阴影大小
        bank_cardView.setBackgroundResource(R.mipmap.bankbackground);
        bianji_bankcard = holder.findViewById(R.id.bianji_bankcard);
        if (item.isCheck){
            bianji_bankcard.setImageResource(R.mipmap.xuanzhong);
        }
        if (!item.isCheck){
            bianji_bankcard.setImageResource(R.mipmap.weixuan);
        }
        if (item.isClick) {
            bianji_bankcard.setVisibility(View.VISIBLE);
        } else {
            bianji_bankcard.setVisibility(View.GONE);
        }
        bianji_bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isCheck) {
                    choseBank.setbank(layoutPosition, !item.isCheck);
                } else {
                    choseBank.setbank(layoutPosition, !item.isCheck);
                }
            }
        });

    }
}
