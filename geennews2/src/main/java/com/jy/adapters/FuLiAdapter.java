package com.jy.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geennews2.R;
import com.jy.beans.GankBeans;
import com.jy.beans.GankGrilBeans;

import java.util.ArrayList;
import java.util.List;

public class FuLiAdapter extends RecyclerView.Adapter<FuLiAdapter.ViewHolder> {
    Context context;
    ArrayList<GankGrilBeans.ResultsBean> list = new ArrayList<>();

    public FuLiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fuli_rec_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<GankGrilBeans.ResultsBean> results) {
        if (list!=null){
            list.clear();
            list.addAll(results);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.fuli_img);
        }
    }
}
