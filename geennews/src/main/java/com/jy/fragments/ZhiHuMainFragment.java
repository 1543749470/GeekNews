package com.jy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.geennews.R;
import com.google.android.material.tabs.TabLayout;
import com.jy.adapters.ZhihuMainAdapter;
import com.jy.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZhiHuMainFragment extends BaseFragment {

    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;
    Unbinder unbinder;
    private ArrayList<Fragment> list;
    private ZhihuMainAdapter zhihuMainAdapter;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list.add(new DailyFragment());
        list.add(new ThemeFragment());
        list.add(new SectionFragment());
        list.add(new HotFragment());
        zhihuMainAdapter = new ZhihuMainAdapter(getChildFragmentManager(), list);
        vpZhihuMain.setAdapter(zhihuMainAdapter);
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText("日报");
        tabZhihuMain.getTabAt(1).setText("主题");
        tabZhihuMain.getTabAt(2).setText("专栏");
        tabZhihuMain.getTabAt(3).setText("热门");
        tabZhihuMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        tr.show(new DailyFragment());
                        tr.hide(new ThemeFragment());
                        tr.hide(new SectionFragment());
                        tr.hide(new HotFragment());
                        break;
                    case 1:
                        tr.show(new ThemeFragment());
                        tr.hide(new DailyFragment());
                        tr.hide(new SectionFragment());
                        tr.hide(new HotFragment());
                        break;
                    case 2:
                        tr.show(new SectionFragment());
                        tr.hide(new ThemeFragment());
                        tr.hide(new DailyFragment());
                        tr.hide(new HotFragment());
                        break;
                    case 3:
                        tr.show(new HotFragment());
                        tr.hide(new ThemeFragment());
                        tr.hide(new SectionFragment());
                        tr.hide(new DailyFragment());
                        break;

                }
                tr.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu_main;
    }


}
