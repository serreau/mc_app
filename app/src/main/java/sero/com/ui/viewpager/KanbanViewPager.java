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
    @BindView(R.id.action_button) com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton action_button;
    @BindView(R.id.viewpager) ViewPager viewPager;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_pager, container, false);
        ButterKnife.bind(this, view);

        viewPager.setAdapter(new KanbanPagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(1);
        viewPager.bringToFront();

        setListeners();

        return view;
    }

    private void setListeners() {
        action_button.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                        R.id.action_kanbanViewPager_to_createJobFragment
                ));
        action_button.setOnLongClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPager_to_kanbansFragment);
            return true;
        });
    }
}
