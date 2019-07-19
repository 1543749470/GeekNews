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

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    Context context;
    ArrayList<ThemeBeans.RecentBean> list = new ArrayList<>();

    public ThemeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.theme_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ThemeBeans.RecentBean recentBean = list.get(position);
        holder.tv.setText(recentBean.getTitle());
        Glide.with(context)
                .load(recentBean.getThumbnail())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhiHuDetailsActivity.class);
                intent.putExtra("id",recentBean.getNews_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void initData(List<ThemeBeans.RecentBean> recent) {
        if (list!=null){
            list.clear();
            list.addAll(recent);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.theme_img);
            tv = itemView.findViewById(R.id.theme_tv);
        }
    }
}
