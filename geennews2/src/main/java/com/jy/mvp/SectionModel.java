package com.jy.mvp;

import android.util.Log;

import com.example.geennews2.ZhihuApis;
import com.jy.Base.BaseModel;
import com.jy.Base.CallBack;
import com.jy.beans.SectionListBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SectionModel extends BaseModel {
    private static final String TAG = "SectionModel";
    public void getSectionListData(final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis zhihuApis = retrofit.create(ZhihuApis.class);
        zhihuApis.getSectionList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectionListBean value) {
                        callBack.updateDataSuccess(value);
                        Log.e(TAG, "onNext: "+value );
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.updateDataFailed(e.getMessage());
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
