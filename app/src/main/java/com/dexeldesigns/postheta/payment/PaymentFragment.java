package com.dexeldesigns.postheta.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.fragments.BookFragments;
import com.dexeldesigns.postheta.fragments.TakeAway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Creative IT Works on 02-Aug-17.
 */

public class PaymentFragment extends Fragment  {
    TabLayout tabLayout;
    ViewPager viewPager;
    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment, container, false);

        initUI(view);
         bundle = this.getArguments();


        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange));

        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getActivity(),android.R.color.white),
                ContextCompat.getColor(getActivity(), R.color.orange)
        );
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
    private void initUI(View view) {
          viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);


    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragment.setArguments(bundle);
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new CashFragment(), "CASH");
        adapter.addFragment(new CashFragment(), "CARD");
        viewPager.setAdapter(adapter);

    }


}
