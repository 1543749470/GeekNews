package com.jy.mvp;

import com.jy.base.BaseView;
import com.jy.beans.DailyBeforeBean;
import com.jy.beans.DailyListBean;

public interface DailyView extends BaseView {
    void getCurrData(DailyListBean dailyListBean);
    // 获取以前日报数据
    void getBeforeData(DailyBeforeBean dailyBeforeBean);

}
