package com.jy.Fragments.gank;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.geennews2.R;
import com.google.android.material.tabs.TabLayout;
import com.jy.Base.BaseFragment;
import com.jy.adapters.ZhihuMainAdapter;
import com.jy.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

public class GankMainFragment extends BaseFragment {

    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;
    Unbinder unbinder;
    private ArrayList<Fragment> list;
    private ZhihuMainAdapter zhihuMainAdapter;
    public static String[] tabTitle = new String[]{"Android", "iOS", "前端", "福利"};

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

         initBroadcastReceiver();
        //android  页面 传送数据
        final GankCommonFragment android = new GankCommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE,tabTitle[0]);
        android.setArguments(bundle);
        //ios  页面 传送数据
        final GankCommonFragment ios = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE,tabTitle[1]);
        ios.setArguments(bundle);
        //前端  页面 传送数据
        final GankCommonFragment web = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE,tabTitle[2]);
        web.setArguments(bundle);
        //福利  页面 传送数据
        final GankFuLiFragment fuli = new GankFuLiFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE,tabTitle[3]);
        fuli.setArguments(bundle);
        list.add(android);
        list.add(ios);
        list.add(web);
        list.add(fuli);
        zhihuMainAdapter = new ZhihuMainAdapter(getChildFragmentManager(), list);
        vpZhihuMain.setAdapter(zhihuMainAdapter);
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText("Android");
        tabZhihuMain.getTabAt(1).setText("iOS ");
        tabZhihuMain.getTabAt(2).setText("前端");
        tabZhihuMain.getTabAt(3).setText("福利");
        tabZhihuMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        tr.show(android);
                        tr.hide(ios);
                        tr.hide(web);
                        tr.hide(fuli);
                        break;
                    case 1:
                        tr.show(ios);
                        tr.hide(android);
                        tr.hide(web);
                        tr.hide(fuli);
                        break;
                    case 2:
                        tr.show(web);
                        tr.hide(android);
                        tr.hide(fuli);
                        tr.hide(ios);
                        break;
                    case 3:
                        tr.show(fuli);
                        tr.hide(android);
                        tr.hide(ios);
                        tr.hide(web);
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

    private void initBroadcastReceiver() {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(gankBroadcastReceiver,new IntentFilter("com.gank.search"));
    }
    BroadcastReceiver gankBroadcastReceiver = new BroadcastReceiver(){
        private static final String TAG = "GankMainFragment";
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: "+intent.getStringExtra("data"));
        }
    };
    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(gankBroadcastReceiver);
    }
}
