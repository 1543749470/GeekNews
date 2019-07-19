package com.jy.fragments;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geennews.R;
import com.jy.adapters.MyLikeAdapter;
import com.jy.base.BaseFragment;
import com.jy.beans.MyLikeBeans;
import com.jy.dao.DBUtils;

import java.util.List;

import butterknife.BindView;

public class MyLikeFragment extends BaseFragment {
    @BindView(R.id.myRec)
    RecyclerView myRec;
    private MyLikeAdapter myLikeAdapter;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        }else{
            initData();
        }
    }

    private void initData() {
        List<MyLikeBeans> myLikeBeans = DBUtils.queryAll();
        if (myLikeAdapter!=null){
            myLikeAdapter.addData(myLikeBeans);
        }
    }

    @Override
    protected void initView() {
        myRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        myLikeAdapter = new MyLikeAdapter(getActivity());
        myRec.setAdapter(myLikeAdapter);
    }

    @Override
    protected void initPresenter() {
        initData();
    }

    @Override
    protected int getLayout() {
        return R.layout.mylike_layout;
    }
}
