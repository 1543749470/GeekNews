package com.jy.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseAcctivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initMvp();
        initView();
        initListener();
    }

    private void initListener() {

    }

    protected abstract void initView();

    protected abstract void initMvp();

    protected abstract int getLayout();
}
