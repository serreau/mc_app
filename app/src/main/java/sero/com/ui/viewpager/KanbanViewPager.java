package sero.com.ui.viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import sero.com.ui.R;
import sero.com.ui.adapter.KanbanPagerAdapter;

public class KanbanViewPager extends Fragment {

    View view;
    @BindView(R.id.kanbanviewpager) ViewPager viewPager;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.kanbanviewpager, container, false);
        ButterKnife.bind(this, view);

        viewPager.setAdapter(new KanbanPagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(1);
        viewPager.bringToFront();


        return view;
    }


}
