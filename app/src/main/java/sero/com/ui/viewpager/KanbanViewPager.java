package sero.com.ui.viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import sero.com.ui.R;
import sero.com.ui.adapter.KanbanPagerAdapter;

public class KanbanViewPager extends Fragment {

    View view;
    ViewPager viewPager;

    private ScaleGestureDetector mScaleGestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_pager, container, false);



        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new KanbanPagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(1);


        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScaleGestureDetector.onTouchEvent(event);
                return mScaleGestureDetector.isInProgress();
            }
        });

        return view;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPager_to_kanbansFragment);
            return true;
        }
    }


}
