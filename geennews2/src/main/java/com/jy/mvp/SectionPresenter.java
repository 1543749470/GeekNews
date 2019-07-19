package com.jy.mvp;

import com.jy.Base.BasePresenter;
import com.jy.Base.CallBack;
import com.jy.beans.SectionListBean;

public class SectionPresenter extends BasePresenter<SectionView,SectionModel> {


    public SectionPresenter(SectionView view) {
        super(view);
    }

    @Override
    protected SectionModel initModel() {
        return new SectionModel();
    }

    public void getSectionList(){

        model.getSectionListData(new CallBack<SectionListBean>() {
            @Override
            public void updateDataSuccess(SectionListBean sectionListBean) {

                view.getSectionListBean(sectionListBean);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }
}
