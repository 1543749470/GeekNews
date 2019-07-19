package com.jy.Fragments.gold;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.geennews2.GoldManagerActivity;
import com.example.geennews2.R;
import com.google.android.material.tabs.TabLayout;
import com.jy.Base.BaseFragment;
import com.jy.adapters.ZhihuMainAdapter;
import com.jy.utils.Constants;
import com.jy.utils.GoldStatus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GoldMainFragment extends BaseFragment {
    List<Fragment> fragments=new ArrayList<>();
    @BindView(R.id.tab_gold_main)
    TabLayout tabGoldMain;
    @BindView(R.id.iv_gold_menu)
    ImageView ivGoldMenu;
    @BindView(R.id.vp_gold_main)
    ViewPager vpGoldMain;
    Unbinder unbinder;
    private ZhihuMainAdapter zhihuMainAdapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        IntentFilter intentFilter = new IntentFilter("update.gold");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,intentFilter);
        tabGoldMain.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabGoldMain.setupWithViewPager(vpGoldMain);
        updateTabData();
    }

    public void updateTabData(){
        fragments.clear();
        tabGoldMain.removeAllTabs();
        for (int x = 0; x < Constants.goldStatuses.size(); x++) {
            GoldStatus goldStatus = Constants.goldStatuses.get(x);
            if (goldStatus.isSelected()){
                GoldCommonFragment common = new GoldCommonFragment();
                tabGoldMain.addTab(tabGoldMain.newTab().setText(goldStatus.getTitle()).setTag(x));
                fragments.add(common);
            }
        }
        zhihuMainAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments);
        vpGoldMain.setAdapter(zhihuMainAdapter);
        tabGoldMain.setupWithViewPager(vpGoldMain);
        int currIndex=0;
        for (int i = 0; i <Constants.goldStatuses.size(); i++) {
            GoldStatus goldStatus = Constants.goldStatuses.get(i);
            if (goldStatus.isSelected()){
                String title = Constants.goldStatuses.get(i).getTitle();
                tabGoldMain.getTabAt(currIndex++).setText(title);
            }
        }
    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold_main;
    }
    @OnClick(R.id.iv_gold_menu)
    public void onClick(){
        Intent intent = new Intent(getActivity(), GoldManagerActivity.class);
        startActivity(intent);
    }

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private static final String TAG = "GoldMainFragment";
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: " );
            updateTabData();
        }
    };
}
