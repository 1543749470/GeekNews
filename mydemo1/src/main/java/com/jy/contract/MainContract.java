package com.jy.contract;

import com.jy.bean.ListData;

import java.util.List;

public interface MainContract {
    interface MainView{
        void showSuccess(List<ListData> listData);
        void showError(String error);
    }
    interface MainPresenter{
        void http();

    }
    interface MainModel{
        interface CallBack{
            void showSuccess(List<ListData> listData);
            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
