package com.jy.Fragments.like;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geennews2.R;
import com.jy.Base.BaseFragment;
import com.jy.adapters.MyLikeAdapter;
import com.jy.beans.MyLikeBeans;
import com.jy.greendao.Utils;

import java.util.List;

import butterknife.BindView;

public class MyLike extends BaseFragment {
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

    @Override
    protected void initListener() {
        initData();
    }

    private void initData() {
        List<MyLikeBeans> myLikeBeans = Utils.queryAll();
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

    }

    @Override
    protected int getLayout() {
        return R.layout.mylike_layout;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (myLikeAdapter != null) {
            List<MyLikeBeans> collectionDbBeans = Utils.queryAll();
            myLikeAdapter.addData(collectionDbBeans);
        }
    }
}
