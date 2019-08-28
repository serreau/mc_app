package sero.com.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sero.com.ui.dashboard.DashboardFragment;
import sero.com.ui.detailJob.DetailsFragment;
import sero.com.ui.done.DoneFragment;
import sero.com.ui.inprogress.InProgressFragment;
import sero.com.ui.offer.OfferFragment;
import sero.com.ui.todo.ToDoFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    private Long jobId;

    public DetailPagerAdapter(FragmentManager fm, Long jobName) {
        super(fm);
        this.jobId = jobName;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle b = new Bundle();
        b.putLong("id", jobId);
        switch (i){
            case 0 :
                Fragment detail = new DetailsFragment();
                detail.setArguments(b);
                return detail;
            case 1 :
                Fragment offer = new OfferFragment();
                offer.setArguments(b);
                return offer;
            default:
                return null;
        }

    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
