package com.jy.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geennews2.R;
import com.example.geennews2.ZhiHuDetailsActivity;
import com.jy.Base.BaseFragment;
import com.jy.adapters.CollectionListAdapter;
import com.jy.beans.MyLikeBeans;
import com.jy.greendao.Utils;

import java.util.List;

import butterknife.BindView;

public class CollectionFragment extends BaseFragment {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    String action ="com.like.search";

    private static final String TAG = "CollectionFragment";
    private CollectionListAdapter collectionListAdapter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive: data="+intent.getStringExtra("data"));
            // TODO 查询数据库 by title  注意：like
            // TODO 刷新UI



        }
    };


    @Override
    protected void initListener() {

    }

    protected void initView() {


        IntentFilter intentFilter = new IntentFilter(action);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,intentFilter);

        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        collectionListAdapter = new CollectionListAdapter(getActivity());
        viewMain.setAdapter(collectionListAdapter);


        List<MyLikeBeans> collectionDbBeans = Utils.queryAll();

        collectionListAdapter.initData(collectionDbBeans);
        collectionListAdapter.setItemClickListener(new CollectionListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int type, int id) {

                Log.d(TAG, "onItemClick: " + type + "---id=" + id);

                if (type == 1) {
                    Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                } else if (type == 2) {

                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (collectionListAdapter != null) {
            List<MyLikeBeans> collectionDbBeans = Utils.queryAll();
            collectionListAdapter.initData(collectionDbBeans);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        Log.d(TAG, "onHiddenChanged: " + hidden);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
