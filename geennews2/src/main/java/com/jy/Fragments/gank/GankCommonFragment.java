package com.jy.Fragments.gank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.google.android.material.appbar.AppBarLayout;
import com.jy.Base.BaseMvpFragment;
import com.jy.adapters.GankAdapter;
import com.jy.adapters.SectionAdapter;
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

public class GankCommonFragment extends BaseMvpFragment<GankPresenter> implements GankView {

    private static final String TAG = "GankCommonFragment";
    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView ivTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView tvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    private GankAdapter gankAdapter;
    private String key;
    private View view;

    @Override
    protected GankPresenter initMvp() {
        return new GankPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        Bundle arguments = getArguments();
        key = arguments.getString(Constants.IT_GANK_TYPE);

        Glide.with(getActivity()).load("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg").into(ivTechBlur);
        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        tvTechCopyright.setText("万丈红尘三杯酒,千秋霸业一盏茶");
        viewMain.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
         gankAdapter= new GankAdapter(getActivity());
        viewMain.setAdapter(gankAdapter);
        mPresenter.getGankList(key);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&mPresenter!=null){
            mPresenter.getGankList(key);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.gank_fragment_layout;
    }



    @Override
    public void showErrorMsg(String error) {
        Log.e(TAG, "showErrorMsg: " + error);
    }




    @Override
    public void getGankListData(GankBeans gankBeans) {
        if (gankAdapter!=null){
            gankAdapter.initData(gankBeans.getResults());
        }
    }

    @Override
    public void getGankGrilListData(GankGrilBeans gankBeans) {

    }
}
