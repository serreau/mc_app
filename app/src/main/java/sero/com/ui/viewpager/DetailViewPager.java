package sero.com.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.ui.adapter.DetailPagerAdapter;

public class DetailViewPager extends Fragment {

    View view;

    @BindView(R.id.detailViewPager) ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detailviewpager, container, false);
        ButterKnife.bind(this, view);

        Long jobId = getArguments().getLong("id", 0);


        viewPager.setAdapter(new DetailPagerAdapter(getChildFragmentManager(), jobId));
        viewPager.setCurrentItem(0);
        viewPager.bringToFront();

        return view;
    }
}
