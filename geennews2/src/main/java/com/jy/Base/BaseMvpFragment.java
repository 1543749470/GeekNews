package com.jy.Base;

public abstract class BaseMvpFragment<P extends BasePresenter>extends BaseFragment {
    protected P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter=initMvp();
    }

    protected abstract P initMvp();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter=null;
    }
}
