package com.jy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.jy.beans.GankBeans;
import com.jy.beans.Newslist;

import java.util.ArrayList;
import java.util.List;

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    List<GankBeans.ResultsBean> dataBeanList = new ArrayList<>();
    Context context;

    public GankAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<GankBeans.ResultsBean> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public GankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_tech, viewGroup, false);
        return new GankViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull GankViewHolder sectionViewHolder, int i) {


        GankBeans.ResultsBean dataBean = dataBeanList.get(i);
        if (dataBean.getImages()!=null&&dataBean.getImages().size()>0){
            Glide.with(context).load(dataBean.getImages().get(0)).into(sectionViewHolder.img);
        }

        sectionViewHolder.title.setText(dataBean.getDesc());
        sectionViewHolder.author.setText(dataBean.getWho());
        sectionViewHolder.time.setText(dataBean.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class GankViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title, author,time;

        public GankViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_tech_icon);
            title = itemView.findViewById(R.id.tv_tech_title);
            author = itemView.findViewById(R.id.tv_tech_author);
            time = itemView.findViewById(R.id.tv_tech_time);
        }
    }
}
