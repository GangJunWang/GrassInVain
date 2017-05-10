package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct.AlloederFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct.AlreadyPayFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct.WaitPayFragment;
import bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct.WaitSpeakFragment;

/**
 * Created by 1 on 2017/4/7.
 */

public class MyTabViewpagerThreeAdapter extends FragmentPagerAdapter {

    private String mTile []  = new String[]{"全部","未支付","已支付","待评价"};

    public MyTabViewpagerThreeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (1 == position){
            return  new WaitPayFragment();
        }
        if (2 == position){
            return new AlreadyPayFragment();
        }
        if (3 == position){
            return new WaitSpeakFragment();
        }
        return new AlloederFragment();
    }

    @Override
    public int getCount() {
        return mTile.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTile[position];
    }
}
