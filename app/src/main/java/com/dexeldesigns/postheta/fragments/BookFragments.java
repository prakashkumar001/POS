package com.dexeldesigns.postheta.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dexeldesigns.postheta.R;


import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Creative IT Works on 31-Jul-17.
 */

public class BookFragments extends Fragment implements ViewPager.OnPageChangeListener {
     NavigationTabBar navigationTabBar;
     PagerAdapter adapter;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.book, container, false);

        initUI(view);




        return view;
    }

    private void initUI(View view) {
        final ViewPager viewPager = (ViewPager)view. findViewById(R.id.viewpager);
        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);

        navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_sample_3);
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);
         adapter= new PagerAdapter
                (getActivity().getSupportFragmentManager(), 3);
         viewPager.setAdapter(adapter);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.dinein), Color.parseColor("#999999")).build());

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.takeaways),Color.parseColor("#999999")).build());
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.uber), Color.parseColor("#999999")).build());


        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(this);

        setUiPageViewController();


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselect_item_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selectitem_dot));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TableService tab1 = new TableService();

                    return tab1;
                case 1:
                    TakeAway tab2 = new TakeAway();
                    return tab2;
                case 2:
                    TableService tab3 = new TableService();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    private void setUiPageViewController() {

        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselect_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selectitem_dot));
    }

}
