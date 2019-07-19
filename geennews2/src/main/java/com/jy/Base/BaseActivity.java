package com.jy.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        if (savedInstanceState!=null){
            resumeDataState(savedInstanceState);
        }
        ButterKnife.bind(this);
        initMvp();
        initView();

        initListener();
    }

    public void resumeDataState(Bundle savedInstanceState) {

    }

    protected abstract void initView();

    private void initListener() {

    }



    protected abstract void initMvp();


    protected abstract int getLayout();
}
