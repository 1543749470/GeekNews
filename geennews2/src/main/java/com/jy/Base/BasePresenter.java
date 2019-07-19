package com.jy.Base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {
    protected V view;
    protected M model;
    List<M> modelList=new ArrayList<M>();

    public BasePresenter(V view) {
        this.view = view;
        this.model=initModel();
        modelList.add(model);
    }

    protected abstract M initModel();
    public void destory(){
        view=null;
        //切断网络
        if (modelList!=null){
            for (M model:modelList) {
                model.destory();
            }
        }
    }
}
