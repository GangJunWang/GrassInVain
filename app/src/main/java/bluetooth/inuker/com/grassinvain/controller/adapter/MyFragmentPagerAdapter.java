package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bluetooth.inuker.com.grassinvain.controller.fragment.yejifragnemt.SalesResultSalesteam;
import bluetooth.inuker.com.grassinvain.controller.fragment.yejifragnemt.SalesResultperson;

/**
 * Created by 1 on 2017/4/6.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String mTile[] = new String[]{"个人业绩","团队业绩"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (1 == position){
            return  new SalesResultSalesteam();
        }
        return new SalesResultperson();
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
