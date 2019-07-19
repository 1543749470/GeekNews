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
import com.jy.beans.ThemeBeans;
import com.jy.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class ReMenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ThemeBeans.RecentBean> list = new ArrayList<>();

    public ReMenAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.zhihu_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.tv.setText(list.get(i).getTitle());
//        Glide.with(context).load(list.get(i).getThumbnail()).into(holder.img);
        ImageLoaderUtils.showImg(list.get(i).getThumbnail(),(holder.img));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZhiHuDetailsActivity.class);
                intent.putExtra("id",list.get(i).getNews_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<ThemeBeans.RecentBean> recent) {
        if (list!=null){
            list.clear();
            list.addAll(recent);
            notifyDataSetChanged();

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.zhihu_tv);
            img = itemView.findViewById(R.id.zhihu_img);
        }
    }
}
