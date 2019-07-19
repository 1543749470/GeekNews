package com.jy.Fragments.zhihu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geennews2.R;
import com.jy.Base.BaseMvpFragment;
import com.jy.adapters.SectionAdapter;
import com.jy.beans.SectionListBean;
import com.jy.mvp.SectionPresenter;
import com.jy.mvp.SectionView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SectionFragment extends BaseMvpFragment<SectionPresenter> implements SectionView {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private static final String TAG = "SectionFragment";
    private SectionAdapter sectionAdapter;

    @Override
    protected SectionPresenter initMvp() {
        return new SectionPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        viewMain.setLayoutManager(new GridLayoutManager(getActivity(),2));
        viewMain.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        sectionAdapter = new SectionAdapter(getActivity());
        viewMain.setAdapter(sectionAdapter);
        mPresenter.getSectionList();
        
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    @Override
    public void getSectionListBean(SectionListBean sectionListBean) {
        if (sectionAdapter!=null){
            sectionAdapter.initData(sectionListBean.getData());
        }
    }

    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, "showErrorMsg: "+error );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&mPresenter!=null){
            mPresenter.getSectionList();
        }
    }
    public void doNoImgMode(){
        if (sectionAdapter!=null){
            sectionAdapter.notifyDataSetChanged();
        }
    }
}
