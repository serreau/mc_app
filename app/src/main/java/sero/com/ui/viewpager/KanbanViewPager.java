package sero.com.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sero.com.ui.R;
import sero.com.ui.adapter.KanbanPagerAdapter;

public class KanbanViewPager extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager, container, false);

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new KanbanPagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(1);

        return view;
    }

}
