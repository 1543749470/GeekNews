package com.jy.base;

public abstract class BaseMvpActivity<P extends BasePresenter>extends BaseAcctivity {
    protected P mPresenter;

    @Override
    protected void initMvp() {
        mPresenter=initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destory();
    }
}
