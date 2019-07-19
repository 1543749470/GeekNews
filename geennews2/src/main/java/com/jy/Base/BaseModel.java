package com.jy.Base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {
    //网络切断逻辑
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void destory(){
        compositeDisposable.clear();
    }
}
