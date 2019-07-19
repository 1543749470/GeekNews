package com.jy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geennews.R;
import com.jy.adapters.FuLiAdapter;
import com.jy.adapters.SectionAdapter;
import com.jy.base.BaseMvpFragment;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;
import com.jy.beans.SectionListBean;
import com.jy.mvp.SectionPresenter;
import com.jy.mvp.SectionView;
import com.jy.mvp.gank.GankPresenter;
import com.jy.mvp.gank.GankView;
import com.jy.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankFuLiFragment extends BaseMvpFragment<GankPresenter> implements GankView {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private FuLiAdapter fuLiAdapter;


    @Override
    protected GankPresenter initMvp() {
        return new GankPresenter(this);
    }

    @Override
    protected void initView() {

        viewMain.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        fuLiAdapter = new FuLiAdapter(getActivity());
        viewMain.setAdapter(fuLiAdapter);
        mPresenter.getGankGrilList();
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    private static final String TAG = "GankFuLiFragment";
    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, "showErrorMsg: "+error );
    }






    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&mPresenter!=null){
            mPresenter.getGankGrilList();
        }
    }

    @Override
    public void getGankListData(GankBeans gankBeans) {

    }

    @Override
    public void getGankGrilListData(GankGrilBeans gankBeans) {
        if (fuLiAdapter!=null){
            Log.e(TAG, "getGankListData: "+gankBeans.getResults() );
            fuLiAdapter.addData(gankBeans.getResults());
        }
    }
}
