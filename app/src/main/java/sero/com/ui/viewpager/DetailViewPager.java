package sero.com.ui.viewpager;

import android.arch.lifecycle.ViewModelProviders;
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
import sero.com.ui.offers.OffersViewModel;

public class DetailViewPager extends Fragment {
    private View view;
    private DetailsPagerViewModel viewModel;

    @BindView(R.id.detailViewPager) ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detailviewpager, container, false);
        viewModel = ViewModelProviders.of(this).get(DetailsPagerViewModel.class);
        ButterKnife.bind(this, view);

        Long jobId = getArguments().getLong("id", 0);
        viewModel.setJobId(jobId);

        viewModel.isOwner().observe(this, isOwner -> {
            viewPager.setAdapter(new DetailPagerAdapter(getChildFragmentManager(), jobId, isOwner));
        });

        viewPager.setCurrentItem(0);
        viewPager.bringToFront();

        return view;
    }
}
