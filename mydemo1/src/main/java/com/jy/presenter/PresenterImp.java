package com.jy.presenter;

import com.jy.base.BasePresenter;
import com.jy.bean.ListData;
import com.jy.contract.MainContract;
import com.jy.modle.ModelImp;

import java.util.List;

public class PresenterImp extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter{
    private ModelImp modelImp;
    MainContract.MainView mView;
    public PresenterImp(MainContract.MainView mView) {
        this.mView=mView;
        modelImp=new ModelImp();
    }

    @Override
    public void http() {
        modelImp.getData(new MainContract.MainModel.CallBack() {
            @Override
            public void showSuccess(List<ListData> listData) {
                mView.showSuccess(listData);
            }

            @Override
            public void showError(String error) {
                mView.showError(error);
            }
        });
    }
}
