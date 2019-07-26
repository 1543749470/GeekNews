package com.example.mydemo1;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.adapters.MyRecAdapter;
import com.jy.base.BaseActivity;
import com.jy.base.BasePresenter;
import com.jy.bean.ListData;
import com.jy.contract.MainContract;
import com.jy.presenter.PresenterImp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainContract.MainView,BasePresenter<MainContract.MainView>> implements MainContract.MainView {


    @BindView(R.id.myRec)
    RecyclerView myRec;
    private MyRecAdapter myRecAdapter;

    @Override
    protected void initViewAndData() {
        myRec.setLayoutManager(new LinearLayoutManager(this));
        myRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        new PresenterImp(this).http();
        myRecAdapter = new MyRecAdapter(this);
        myRec.setAdapter(myRecAdapter);

    }



    @Override
    protected int createLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter;
    }

    private static final String TAG = "MainActivity";

    @Override
    public void showSuccess(List<ListData> listData) {
        Log.e(TAG, "showSuccess: "+listData );
        myRecAdapter.addData(listData);
    }

    @Override
    public void showError(String error) {

    }
}
