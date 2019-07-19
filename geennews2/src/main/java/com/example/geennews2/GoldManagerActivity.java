package com.example.geennews2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.Base.BaseActivity;
import com.jy.adapters.GoldManagerAdapter;
import com.jy.utils.Constants;
import com.jy.utils.DefaultItemTouchHelpCallback;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldManagerActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private GoldManagerAdapter goldManagerAdapter;
    private DefaultItemTouchHelpCallback mCallback;

    @Override
    protected void initView() {
        toolBar.setTitle("选择tab");
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        goldManagerAdapter = new GoldManagerAdapter(this);
        recyclerview.setAdapter(goldManagerAdapter);
        mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (Constants.goldStatuses!=null){
                    Collections.swap(Constants.goldStatuses,srcPosition,targetPosition);
                    goldManagerAdapter.notifyItemMoved(srcPosition,targetPosition);
                    return true;
                }
                return false;
            }
        });
        mCallback.setDragEnable(true);
        mCallback.setSwipeEnable(false);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    @Override
    protected void initMvp() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_gold_manager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction("update.gold");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
