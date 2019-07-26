package com.jy.utils;

import com.jy.bean.BaseResponse;
import com.jy.http.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    //简化线程切换的代码
    public static  <T>ObservableTransformer<T,T>rxScheduleThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    //将数据转换成我们想要的数据
    public static  <T> ObservableTransformer<BaseResponse<T>,T>changeResult(){
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final BaseResponse<T> tBaseResponse) throws Exception {
                        if (tBaseResponse.getErrorCode()==0){
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                    try{
                                        emitter.onNext(tBaseResponse.getData());
                                        emitter.onComplete();
                                    }catch (Exception e){
                                        emitter.onError(e);
                                    }
                                }
                            });
                        }else{
                            return Observable.error(new ApiException(tBaseResponse.getErrorCode(),tBaseResponse.getErrorMsg()));
                        }
                    }
                });
            }
        };
    }
}
