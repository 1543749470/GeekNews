package com.jy.modle;

import android.util.Log;

import com.jy.app.Globle;
import com.jy.bean.ListData;
import com.jy.contract.MainContract;
import com.jy.http.HttpManager;
import com.jy.http.MySercer;
import com.jy.utils.RxUtils;

import org.greenrobot.greendao.DbUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ModelImp   implements MainContract.MainModel {
    private static final String TAG = "ModelImp";

    @Override
    public void getData(final CallBack callBack) {

        HttpManager.getInstance().getServer(MySercer.class)
                .get("wxarticle/chapters/json")
                .compose(RxUtils.<List<ListData>>changeResult())
                .compose(RxUtils.<List<ListData>>rxScheduleThread())
                .subscribe(new Observer<List<ListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(List<ListData> value) {
                        Log.e(TAG, "onNext: "+value );
                        callBack.showSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                        callBack.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }
}
