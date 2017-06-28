package com.example.administrator.newsclient;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/27.
 */

public class MainFragment1 extends  BaseFragment{
    String TAG="111111111";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    final String[] titles = {
            "头条","社会","科技","财经","体育","汽车"
    };
    final String[] channelId ={
            "T1348647909107",
            "T1348648037603",
            "T1348649580692",
            "T1348648756099",
            "T1348649079062",
            "T1348654060988"
    };
    @Override
    protected void initView() {
        viewPager  = (ViewPager) super.mRoot.findViewById(R.id.view_pager_02);
        tabLayout = (TabLayout) super.mRoot.findViewById(R.id.tab_layout);

    }
    @Override
    protected void intitData() {
        initViewPager();
    }

    @Override
    protected void inintListener() {

    }



    private void initViewPager() {

        final List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            NewsFragment fragment = new NewsFragment();
            fragment.setChannelId(channelId[i]);
            fragments.add(fragment);
        }
        Log.i(TAG, "initViewPager: "+fragments.size());
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                Log.i(TAG, "getPageTitle: "+position);
                return titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_01;
    }
}
