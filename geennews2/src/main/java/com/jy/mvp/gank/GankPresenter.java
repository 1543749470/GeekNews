package com.jy.mvp.gank;

import com.jy.Base.BasePresenter;
import com.jy.Base.CallBack;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;

public class GankPresenter extends BasePresenter<GankView,GankModel> {
    public GankPresenter(GankView view) {
        super(view);
    }

    @Override
    protected GankModel initModel() {
        return new GankModel();
    }
    public void getGankList(String key){
        model.getGankList(key, new CallBack<GankBeans>() {
            @Override
            public void updateDataSuccess(GankBeans o) {
                view.getGankListData(o);
            }

            @Override
            public void updateDataFailed(String error) {
                view.showErrorMsg(error);
            }
        });
    }

    public void getGankGrilList(){
        model.getGankGrilList(new CallBack<GankGrilBeans>() {
            @Override
            public void updateDataSuccess(GankGrilBeans o) {
                view.getGankGrilListData(o);
            }

            @Override
            public void updateDataFailed(String error) {
                view.showErrorMsg(error);
            }
        });
    }
}
