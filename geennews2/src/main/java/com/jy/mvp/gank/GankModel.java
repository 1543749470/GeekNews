package com.jy.mvp.gank;

import android.util.Log;

import com.example.geennews2.GankApiService;
import com.jy.Base.BaseModel;
import com.jy.Base.CallBack;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel extends BaseModel {
    private static final String TAG = "GankModel";
    public void getGankList(String key, final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApiService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GankApiService gankApiService = retrofit.create(GankApiService.class);
        gankApiService.getTechList(key,20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GankBeans value) {
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
                        Log.e(TAG, "onComplete: " );
                    }
                });

    }

    public void getGankGrilList(final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApiService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GankApiService gankApiService = retrofit.create(GankApiService.class);
        gankApiService.getGirlList(20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankGrilBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GankGrilBeans value) {
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
                        Log.e(TAG, "onComplete: " );
                    }
                });

    }
}
