package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrdersecondBody;

/**
 * Created by 1 on 2017/4/20.
 */

public class AlloederFragmentAdapter extends SuperAdapter<AllOrderfirstBody> {

    private TextView button1;
    private TextView button2;

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */

    public AlloederFragmentAdapter(Context context, List<AllOrderfirstBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, final AllOrderfirstBody item) {

        TextView danhao = holder.findViewById(R.id.textView22);
        danhao.setText(item.orderNo);
        TextView time = holder.findViewById(R.id.time);
        time.setText(item.createAt);
        TextView hejiprice = holder.findViewById(R.id.textView25);
        if (TextUtil.checkEmpty(item.amount)) {
            String amount = item.amount;
            StringBuilder stringBuilder = new StringBuilder(amount);
            stringBuilder.insert(amount.length() - 2, ".");
            String s = stringBuilder.toString();
            hejiprice.setText(s);
        }
        /**
         * 子布局
         */
        RecyclerView recyclerView = holder.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        List<AllOrdersecondBody> orderInfoList = item.orderInfoList;

        WaitPayAdapter adapter = new WaitPayAdapter(getContext(), orderInfoList, R.layout.order_list_contont);

        recyclerView.setAdapter(adapter);
        /**
         *
         */
        button1 = holder.findViewById(R.id.textView27);

        button2 = holder.findViewById(R.id.textView26);

        if ("PAD".equals(item.status)) {
            button2.setVisibility(View.VISIBLE);
            button1.setText("查看物流");
            button2.setText("确认收货");
            initOnclick(button1, button2, layoutPosition, item);
        } else if ("DPJ".equals(item.status)) {
            button2.setVisibility(View.VISIBLE);
            button1.setText("去评价");
            button2.setText("删除订单");
            initOnclick(button1, button2, layoutPosition, item);
        } else if ("PAY".equals(item.status)) {
            button2.setVisibility(View.VISIBLE);
            button1.setText("取消订单");
            button2.setText("去付款");
            initOnclick(button1, button2, layoutPosition, item);
        } else if ("YPJ".equals(item.status)) {
            button2.setVisibility(View.GONE);
            button1.setText("已评价");
        } else if ("FAL".equals(item.status)) {
            button2.setVisibility(View.GONE);
            button1.setText("已取消");
        }
    }

    private void initOnclick(final TextView button1, final TextView button2, final int layoutPosition, final AllOrderfirstBody item) {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != chooseType) {
                    chooseType.choosebutton1(layoutPosition, button1.getText().toString(), item.orderNo);
                    System.out.println(button1.getText().toString() + layoutPosition);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (null != chooseType) {
                    chooseType.choosebutton2(layoutPosition, button2.getText().toString(), item.orderNo);
                    System.out.println(button2.getText().toString() + layoutPosition);
                }
            }
        });
    }

    public ChooseType chooseType;

    public void setOnItemClickListener(ChooseType chooseType) {
        this.chooseType = chooseType;
    }

    public interface ChooseType {

        void choosebutton1(int positon, String type, String order);

        void choosebutton2(int positon, String type, String order);


    }
}
