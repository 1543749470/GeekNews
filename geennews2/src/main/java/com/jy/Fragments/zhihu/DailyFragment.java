package com.jy.Fragments.zhihu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geennews2.CalenderActivity;
import com.example.geennews2.R;
import com.example.geennews2.ZhiHuDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jy.Base.BaseMvpFragment;
import com.jy.adapters.DailyAdapter;
import com.jy.beans.DailyBeforeBean;
import com.jy.beans.DailyListBean;
import com.jy.mvp.DailyPresenter;
import com.jy.mvp.DailyView;
import com.jy.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DailyFragment extends BaseMvpFragment<DailyPresenter> implements DailyView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    Unbinder unbinder;
    private DailyAdapter dailyAdapter;
    boolean isBefore;
    BroadcastReceiver myBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String date = intent.getStringExtra("date");


            // TODO 是否是当天
            String yyyymmdd = DateUtils.getYYYYMMDD();
            if (date.equals(yyyymmdd)) {
                isBefore = false;
                dailyAdapter.setIsBefore(isBefore,"今日新闻");

                mPresenter.getCurrListData();
            } else {
                isBefore = true;
                dailyAdapter.setIsBefore(isBefore,date);
                mPresenter.getDailyBeforeData(date);
            }


            Log.d(TAG, "onReceive: 日期为=" + date + "---" + isBefore);
        }
    };
    @Override
    protected DailyPresenter initMvp() {
        return new DailyPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        initBroadcastManager();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        dailyAdapter = new DailyAdapter(getActivity());
        recyclerview.setAdapter(dailyAdapter);
        mPresenter.getCurrListData();
        dailyAdapter.setOnItemClick(new DailyAdapter.OnItemClick() {
            @Override
            public void itemClick(int newsId) {
                Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                intent.putExtra("id",newsId);
                startActivity(intent);
            }
        });
    }
    public void initBroadcastManager() {

        IntentFilter intentFilter = new IntentFilter("com.geekdemo.date");

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(myBroadCastReceiver, intentFilter);

    }


    @Override
    protected int getLayout() {
        return R.layout.daily_fragment;
    }
    public void unRegisterBroadcast() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(myBroadCastReceiver);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null){
            unRegisterBroadcast();
            unbinder.unbind();
        }
    }
    @OnClick(R.id.fab_calender)
    public void fabOnClick(View view) {
        startActivity(new Intent(getActivity(), CalenderActivity.class));
    }
    @Override
    public void getCurrData(DailyListBean dailyListBean) {
        dailyAdapter.initBanner(dailyListBean.getTop_stories());
        dailyAdapter.refreshDate(dailyListBean.getDate());
        dailyAdapter.initDailyList(dailyListBean.getStories());
    }

    @Override
    public void getBeforeData(DailyBeforeBean dailyBeforeBean) {
        dailyAdapter.initBeforeData(dailyBeforeBean.getStories());
    }

    private static final String TAG = "DailyFragment";
    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, "showErrorMsg: "+error );
    }


    public void doNoImgMode(){
        if (dailyAdapter!=null){
            dailyAdapter.notifyDataSetChanged();
        }
    }
}
