package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.common.other.SelectPicActivity;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.controller.activity.OrderPeocess;
import bluetooth.inuker.com.grassinvain.controller.activity.ShouyiDetail;
import bluetooth.inuker.com.grassinvain.controller.activity.WorkResult;
import bluetooth.inuker.com.grassinvain.controller.login.LoginActivity;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by Super-me on 2017/3/25.
 */

public class PersonCenterFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView changebackground;
    private LinearLayout gologin;
    private ImageView changeBackPic;
    private ImageView uesrIcon;
    private LinearLayout userInformation, alreadyPay, waitSpeak, yejiChaxun;
    private LinearLayout waitPay;
    private TextView waitPay2, alreadyPay2, waitSpeak2, yejiChaxun2;
    private LinearLayout shouyiDetail;
    private UserModel userModel;
    private TextView current_shouyi;
    private TextView user_class;
    private TextView user_name;
    private UserInfo userInfo1;
    private TextView pricttype;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_person_center, null);
        userModel = new UserModel(getActivity());
        initData();
        initView();
        return view;
    }


    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    /**
     * 得到焦点
     */

    private void initData() {
        userModel.getPersonCentern(new Callback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                userInfo1 = userInfo;
                setPersonCentenr(userInfo);
            }

            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }

    private void setPersonCentenr(UserInfo userInfo) {
        if ("0".equals(userInfo.account.balance)) {
            current_shouyi.setText("0.00");
            current_shouyi.setTextColor(0xffff0000);
        } else {
            String s = TextUtil.fen2yuan(Integer.parseInt(userInfo.account.balance));
            current_shouyi.setText(s);
            current_shouyi.setTextColor(0xffff0000);
        }

        user_class.setText(userInfo.userClassName);
        user_name.setText(userInfo.userName);
        Picasso.with(getActivity()).load(userInfo.avatarUrl).placeholder(R.mipmap.morenback).into(uesrIcon);
    }

    private void initView() {
        //价格单位
        pricttype = (TextView) view.findViewById(R.id.textView30);
        // 当前收益
        current_shouyi = (TextView) view.findViewById(R.id.current_shouyi);
        //用户等级
        user_class = (TextView) view.findViewById(R.id.user_class);
        // 用户名字
        user_name = (TextView) view.findViewById(R.id.user_name);
        //  个人收益详情
        shouyiDetail = (LinearLayout) view.findViewById(R.id.shouyi_detail);
        shouyiDetail.setOnClickListener(this);

        //  底部四个选项卡
        // 等待付款
        waitPay = (LinearLayout) view.findViewById(R.id.wait_pay);
        waitPay2 = (TextView) view.findViewById(R.id.wait_pay2);
        waitPay.setOnClickListener(this);
        waitPay2.setOnClickListener(this);
        //已经付款
        alreadyPay = (LinearLayout) view.findViewById(R.id.already_pay);
        alreadyPay2 = (TextView) view.findViewById(R.id.already_pay2);
        alreadyPay.setOnClickListener(this);
        alreadyPay2.setOnClickListener(this);
        // 等待评价
        waitSpeak = (LinearLayout) view.findViewById(R.id.wait_speak);
        waitSpeak2 = (TextView) view.findViewById(R.id.wait_speak2);
        waitSpeak.setOnClickListener(this);
        waitSpeak2.setOnClickListener(this);
        // 业绩查询
        yejiChaxun = (LinearLayout) view.findViewById(R.id.yeji_survey);
        yejiChaxun2 = (TextView) view.findViewById(R.id.yeji_survey2);
        yejiChaxun.setOnClickListener(this);
        yejiChaxun2.setOnClickListener(this);
        //未登录用户默认图像
        gologin = (LinearLayout) view.findViewById(R.id.linearLayoutMoren);
        gologin.setOnClickListener(this);
        //用户信息
        userInformation = (LinearLayout) view.findViewById(R.id.user_information);
        // 切换背景头像
        changeBackPic = (ImageView) view.findViewById(R.id.imageView2);
        changeBackPic.setOnClickListener(this);
        //背景图片
        uesrIcon = (ImageView) view.findViewById(R.id.user_icon);

        if (!CacheCenter.getInstance().isLogin()) {
            // 这是未登录状态  没有信息展示
            gologin.setVisibility(View.VISIBLE);
            userInformation.setVisibility(View.INVISIBLE);
            changeBackPic.setVisibility(View.INVISIBLE);
            pricttype.setVisibility(View.INVISIBLE);
        } else {
            gologin.setVisibility(View.INVISIBLE);
            userInformation.setVisibility(View.VISIBLE);
            changeBackPic.setVisibility(View.VISIBLE);
            pricttype.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {

        // 先判断是否登录
        if (!CacheCenter.getInstance().isLogin()) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent1);
            return;
        } else {
            Intent intent = new Intent(getActivity(), OrderPeocess.class);
            switch (view.getId()) {
                case R.id.imageView2:
                    CommonUtil.choicePicture(getActivity(), SelectPicActivity.class, 1);
                    break;
                case R.id.wait_pay:
                case R.id.wait_pay2:
                    intent.putExtra("page", "1");
                    startActivity(intent);
                    break;
                case R.id.already_pay:
                case R.id.already_pay2:
                    intent.putExtra("page", "2");
                    startActivity(intent);
                    break;
                case R.id.wait_speak:
                case R.id.wait_speak2:
                    intent.putExtra("page", "3");
                    startActivity(intent);
                    break;
                case R.id.yeji_survey:
                case R.id.yeji_survey2:
                    Intent intentYeji = new Intent(getActivity(), WorkResult.class);
                    startActivity(intentYeji);
                    break;
                case R.id.linearLayoutMoren: {
                    Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intentLogin);
                    break;
                }
                case R.id.shouyi_detail: {
                    Intent intent1 = new Intent(getActivity(), ShouyiDetail.class);
                    intent1.putExtra("detail", userInfo1);
                    startActivity(intent1);
                }
                break;
            }
        }
    }

    // 改变用户头像背景
    public void setImageIdcard(String s) {
        uesrIcon.setImageURI(Uri.parse(s));
    }
}
