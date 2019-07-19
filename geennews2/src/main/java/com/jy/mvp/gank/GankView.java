package com.jy.mvp.gank;

import com.jy.Base.BaseView;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;

public interface GankView extends BaseView {
    void getGankListData(GankBeans gankBeans);
    void getGankGrilListData(GankGrilBeans gankBeans);
}
