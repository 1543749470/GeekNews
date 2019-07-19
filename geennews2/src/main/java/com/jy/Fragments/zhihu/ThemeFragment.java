package com.jy.Fragments.zhihu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geennews2.R;
import com.example.geennews2.ZhihuApis;
import com.jy.adapters.ReMenAdapter;
import com.jy.beans.ThemeBeans;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThemeFragment extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    private SmartRefreshLayout mMySm;
    private ReMenAdapter reMenAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);
        initView(view);
        initData();
        return view;
    }

    private static final String TAG = "ReMenFragment";
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApis.zhihuUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ZhihuApis apiService = retrofit.create(ZhihuApis.class);
        apiService.getR().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThemeBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ThemeBeans reMenBeans) {
                        List<ThemeBeans.RecentBean> recent = reMenBeans.getRecent();
                        Log.e(TAG, "onNext: "+recent );
                        reMenAdapter.addData(recent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMySm = (SmartRefreshLayout) view.findViewById(R.id.mySm);
        mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRec.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        reMenAdapter = new ReMenAdapter(getActivity());
        mMyRec.setAdapter(reMenAdapter);
        mMySm.finishRefresh();
        mMySm.finishLoadMore();

    }
    public void doNoImgMode(){
        if (reMenAdapter!=null){
            reMenAdapter.notifyDataSetChanged();
        }
    }
}
