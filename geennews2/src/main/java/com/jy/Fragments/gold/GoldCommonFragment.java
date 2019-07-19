package com.jy.Fragments.gold;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.jy.Base.BaseMvpFragment;
import com.jy.adapters.GankAdapter;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;
import com.jy.mvp.gank.GankPresenter;
import com.jy.mvp.gank.GankView;
import com.jy.utils.Constants;

import butterknife.BindView;
import butterknife.Unbinder;

public class GoldCommonFragment extends BaseMvpFragment<GankPresenter> implements GankView {

    private static final String TAG = "GoldCommonFragment";

    @Override
    protected GankPresenter initMvp() {
        return new GankPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {


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

    }

    @Override
    public void getGankGrilListData(GankGrilBeans gankBeans) {

    }
}
