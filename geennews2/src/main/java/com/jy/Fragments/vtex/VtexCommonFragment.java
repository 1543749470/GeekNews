package com.jy.Fragments.vtex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geennews2.R;
import com.jy.Base.BaseFragment;
import com.jy.adapters.TopicAdapter;
import com.jy.beans.TopicListBean;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VtexCommonFragment extends BaseFragment {


    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private TopicAdapter topicAdapter;

    String type ="";
    String typeName ="";
    private List<TopicListBean> topicListBeanList;

    @Override
    protected void initListener() {

    }

    protected void initView() {

        typeName = getArguments().getString("typeName");
        type = getArguments().getString("type");


        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        topicAdapter = new TopicAdapter(getActivity());

        viewMain.setAdapter(topicAdapter);


        initData(type);
    }

    public void initData(final String type){


        new Thread(){
            @Override
            public void run() {
                super.run();


                try {
                    Document doc = Jsoup.connect("https://www.v2ex.com/?tab=" + type).timeout(10000).get();

                    Elements itemElements = doc.select("div.cell.item");

                    topicListBeanList = new ArrayList<>();
                    for (int x=0;x<itemElements.size();x++){


                        Elements titleEle = itemElements.get(x).select("div.cell.item table tr td span.item_title > a");
                        Elements imgEle = itemElements.get(x).select("div.cell.item table tr td img.avatar");
                        Elements commentEle = itemElements.get(x).select("div.cell.item table tr a.count_livid");

                        TopicListBean topicListBean = new TopicListBean();

                        if (titleEle.size()>0){
                            topicListBean.setTitle(titleEle.get(0).text());
                        }
                        if (imgEle.size()>0){

                            topicListBean.setImgUrl("http:"+imgEle.get(0).attr("src"));
                        }

                        if (commentEle.size()>0){
                            String topicId = commentEle.get(0).attr("href");

                            String text = commentEle.get(0).text();

                            int i = topicId.indexOf("#");

                            topicId = topicId.substring(3,i);


                            topicListBean.setCommentNum(Integer.valueOf(topicId));
                        }

                        topicListBeanList.add(topicListBean);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (topicAdapter==null){
                            topicAdapter.initData(topicListBeanList);
                        }
                    }
                });

            }
        }.start();


    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

}
