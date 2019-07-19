package com.jy.mvp;

import com.jy.Base.BasePresenter;
import com.jy.Base.CallBack;
import com.jy.beans.DailyBeforeBean;
import com.jy.beans.DailyListBean;

public class DailyPresenter extends BasePresenter<DailyView,DailyModel> {
    public DailyPresenter(DailyView view) {
        super(view);
    }

    @Override
    protected DailyModel initModel() {
        return new DailyModel();
    }
    public void getCurrListData(){
        model.getDailyListData(new CallBack<DailyListBean>() {
            @Override
            public void updateDataSuccess(DailyListBean dailyListBean) {
                view.getCurrData(dailyListBean);
            }

            @Override
            public void updateDataFailed(String error) {
                view.showErrorMsg(error);
            }
        });
    }
    public void getDailyBeforeData(String date){
        model.getDailyBeforeData(date, new CallBack<DailyBeforeBean>() {
            @Override
            public void updateDataSuccess(DailyBeforeBean dailyBeforeBean) {
                view.getBeforeData(dailyBeforeBean);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }
}
