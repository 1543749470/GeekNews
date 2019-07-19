package com.jy.mvp;

import android.util.Log;

import com.example.geennews2.WeChatApis;
import com.jy.Base.BaseModel;
import com.jy.Base.CallBack;
import com.jy.beans.Newslist;
import com.jy.beans.WechatResult;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatModel extends BaseModel {
    private static final String TAG = "WeChatModel";
    public void getWechatList(String key, String num, String page, final CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatApis.wechatUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WeChatApis weChatApis = retrofit.create(WeChatApis.class);
        weChatApis.getWechatList(key,num,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WechatResult<List<Newslist>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WechatResult<List<Newslist>> value) {
                        Log.e(TAG, "onNext: "+value );
                        if (value.getCode()==200){
                            callBack.updateDataSuccess(value);
                        }else{
                            callBack.updateDataFailed(value.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        callBack.updateDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }
}
