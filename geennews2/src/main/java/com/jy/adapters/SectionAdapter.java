package com.jy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.example.geennews2.ZhuanLanActivity;
import com.jy.beans.SectionListBean;
import com.jy.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    List<SectionListBean.DataBean> dataBeanList = new ArrayList<>();
    Context context;

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<SectionListBean.DataBean> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_section, viewGroup, false);
        return new SectionViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder sectionViewHolder, int i) {

//        sectionViewHolder.sectionBg
        final SectionListBean.DataBean dataBean = dataBeanList.get(i);
//        Glide.with(context).load(dataBean.getThumbnail()).into(sectionViewHolder.sectionBg);
        ImageLoaderUtils.showImg(dataBean.getThumbnail(),(sectionViewHolder.sectionBg));
        sectionViewHolder.sectionKind.setText(dataBean.getName());
        sectionViewHolder.sectionDes.setText(dataBean.getDescription());
        sectionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(context, ZhuanLanActivity.class);
                aa.putExtra("llid",dataBean.getId());
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        ImageView sectionBg;
        TextView sectionKind, sectionDes;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionBg = itemView.findViewById(R.id.section_bg);
            sectionKind = itemView.findViewById(R.id.section_kind);
            sectionDes = itemView.findViewById(R.id.section_des);
        }
    }
}
