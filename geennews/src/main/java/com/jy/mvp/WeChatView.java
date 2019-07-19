package com.jy.mvp;

import com.jy.base.BaseView;
import com.jy.beans.Newslist;

import java.util.List;

public interface WeChatView extends BaseView {
    void updateWeChatList(List<Newslist> weChatList);
}
