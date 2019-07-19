package com.example.geennews2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.adapters.ZhuanLanAdapter2;
import com.jy.beans.ZhuanLanBeans2;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuanLanActivity extends AppCompatActivity {

    private RecyclerView mZhuanlanRec;
    private int llid;
    private static final String TAG = "ZhuanLanActivity";
    private ZhuanLanAdapter2 zhuanLanAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_lan);
        initView();
        llid = getIntent().getIntExtra("llid", 0);
        initData();
    }
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis service = retrofit.create(ZhihuApis.class);
        service.getLA(llid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhuanLanBeans2>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ZhuanLanBeans2 zhuanLanBeans2) {
                        List<ZhuanLanBeans2.StoriesBean> stories = zhuanLanBeans2.getStories();
                        Log.e(TAG, "onNext: "+stories );
                        zhuanLanAdapter2.addData(stories);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void initView() {
        mZhuanlanRec = (RecyclerView) findViewById(R.id.zhuanlan_rec);
        mZhuanlanRec.setLayoutManager(new LinearLayoutManager(this));
        mZhuanlanRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        zhuanLanAdapter2 = new ZhuanLanAdapter2(this);
        mZhuanlanRec.setAdapter(zhuanLanAdapter2);
    }
}
