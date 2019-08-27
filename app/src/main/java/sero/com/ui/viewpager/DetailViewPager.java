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
import sero.com.ui.adapter.DetailPagerAdapter;
import sero.com.ui.adapter.KanbanPagerAdapter;

public class DetailViewPager extends Fragment {

    View view;
    //@BindView(R.id.action_button) com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton action_button;
    @BindView(R.id.detailviewpager) ViewPager viewPager;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detailviewpager, container, false);
        ButterKnife.bind(this, view);

        Long jobid = getArguments().getLong("id", 0);


        viewPager.setAdapter(new DetailPagerAdapter(getChildFragmentManager(), jobid));
        viewPager.setCurrentItem(0);
        viewPager.bringToFront();

        //setListeners();

        return view;
    }

    /*private void setListeners() {
        action_button.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                        R.id.action_kanbanViewPager_to_createJobFragment
                ));
        action_button.setOnLongClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPager_to_kanbansFragment);
            return true;
        });
    }*/
}
