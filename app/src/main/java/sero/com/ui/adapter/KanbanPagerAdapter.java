package sero.com.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sero.com.ui.dashboard.DashboardFragment;
import sero.com.ui.searchjob.SearchFragment;

public class KanbanPagerAdapter extends FragmentPagerAdapter {

    public KanbanPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0 :
                return new DashboardFragment();
            case 1 :
                return new SearchFragment();
            case 2 :
                return new SearchFragment();
            case 3 :
                return new SearchFragment();
            default:
                return null;
        }

    }
}
