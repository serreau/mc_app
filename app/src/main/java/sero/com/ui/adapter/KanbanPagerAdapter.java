package sero.com.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sero.com.ui.dashboard.DashboardFragment;
import sero.com.ui.done.DoneFragment;
import sero.com.ui.inprogress.InProgressFragment;
import sero.com.ui.todo.ToDoFragment;

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
                return new ToDoFragment();
            case 2 :
                return new InProgressFragment();
            case 3 :
                return new DoneFragment();
            default:
                return null;
        }

    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
