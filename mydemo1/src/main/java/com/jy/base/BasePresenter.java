package com.jy.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {
    private WeakReference<V> weakReference;

    public V mView;

    public void attch(V view){
        weakReference=new WeakReference<>(view);
        mView=weakReference.get();
    }

    public void detachView(){
        if (weakReference!=null){
            weakReference.clear();
        }
    }
}
