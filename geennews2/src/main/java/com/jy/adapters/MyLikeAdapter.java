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
import com.jy.beans.MyLikeBeans;

import java.util.ArrayList;
import java.util.List;

public class MyLikeAdapter extends RecyclerView.Adapter<MyLikeAdapter.ViewHolder> {
    Context context;
    ArrayList<MyLikeBeans> myLikeBeans = new ArrayList<>();

    public MyLikeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mylike_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyLikeBeans myLikeBeans = this.myLikeBeans.get(position);
        holder.tv.setText(myLikeBeans.getTitle());
        Glide.with(context)
                .load(myLikeBeans.getImage())
                .into(holder.img);
        if (myLikeBeans.getType()==1){
            holder.tv2.setText("来自   知乎");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myLikeBeans.getType()==1){
                    Intent intent = new Intent(context, ZhiHuDetailsActivity.class);
                    intent.putExtra("id",myLikeBeans.getId());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myLikeBeans.size();
    }

    public void addData(List<MyLikeBeans> myLikeBeans) {
        if (this.myLikeBeans!=null){
            this.myLikeBeans.clear();
            this.myLikeBeans.addAll(myLikeBeans);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;
        private TextView tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.rec_img);
            tv = itemView.findViewById(R.id.rec_tv);
            tv2 = itemView.findViewById(R.id.rec_tv2);
        }
    }
}
