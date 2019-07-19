package com.jy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.example.geennews2.ZhiHuDetailsActivity;
import com.jy.beans.ZhuanLanBeans2;
import com.jy.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class ZhuanLanAdapter2 extends RecyclerView.Adapter<ZhuanLanAdapter2.ViewHolder> {
    Context context;
    ArrayList<ZhuanLanBeans2.StoriesBean> list = new ArrayList<>();

    public ZhuanLanAdapter2(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.zhihu_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(list.get(i).getTitle());
//        Glide.with(context).load(list.get(i).getImages().get(0))
//                .into(viewHolder.img);
        ImageLoaderUtils.showImg(list.get(i).getImages().get(0),viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZhiHuDetailsActivity.class);
                intent.putExtra("id",list.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<ZhuanLanBeans2.StoriesBean> stories) {
        if (list!=null){
            list.clear();
            list.addAll(stories);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.zhihu_img);
            tv = itemView.findViewById(R.id.zhihu_tv);
        }
    }
}
