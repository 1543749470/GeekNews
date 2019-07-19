package com.jy.Fragments.wechat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geennews2.R;
import com.jy.Base.BaseMvpFragment;
import com.jy.adapters.WeChatAdapter;
import com.jy.beans.Newslist;
import com.jy.mvp.WeChatPresenter;
import com.jy.mvp.WeChatView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeChatFragment extends BaseMvpFragment<WeChatPresenter> implements WeChatView {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private WeChatAdapter weChatAdapter;

    @Override
    protected WeChatPresenter initMvp() {
        return new WeChatPresenter(this);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewMain.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        weChatAdapter = new WeChatAdapter(getActivity());
        viewMain.setAdapter(weChatAdapter);
        mPresenter.getWeChatListData();
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    private static final String TAG = "WeChatFragment";
    @Override
    public void updateWeChatList(List<Newslist> weChatList) {
        weChatAdapter.initData(weChatList);
        Log.e(TAG, "updateWeChatList: "+weChatList );
    }

    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, "showErrorMsg: "+error );
    }


}
