package com.jy.mvp;

import android.util.Log;

import com.example.geennews.ZhihuApis;
import com.jy.base.BaseModel;
import com.jy.base.CallBack;
import com.jy.beans.DailyBeforeBean;
import com.jy.beans.DailyListBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyModel extends BaseModel {
    private static final String TAG = "DailyModel";
    public void getDailyListData(final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis zhihuApis = retrofit.create(ZhihuApis.class);
        zhihuApis.getLatestList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyListBean value) {
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
    public void getDailyBeforeData(String date, final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis zhihuApis = retrofit.create(ZhihuApis.class);
        zhihuApis.getDailyBeforeList(date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBeforeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyBeforeBean value) {
                        Log.d(TAG, "onNext: "+value);
                        callBack.updateDataSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        callBack.updateDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
