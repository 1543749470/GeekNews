package com.jy.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        bind = ButterKnife.bind(this, view);
        initPresenter();
        initView();
        initListener();
        return view;
    }


    protected abstract void initListener();

    protected abstract void initView();


    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
