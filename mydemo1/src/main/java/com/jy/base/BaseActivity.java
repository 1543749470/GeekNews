package com.jy.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity{
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=createPresenter();
        super.onCreate(savedInstanceState);
        if (mPresenter!=null){
            mPresenter.attch((V) this);
        }
    }
    public void showProgressBar(){}
    public void hideProgressBar(){}

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
