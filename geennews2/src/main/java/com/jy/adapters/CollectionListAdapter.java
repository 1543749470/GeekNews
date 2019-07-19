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
import com.jy.Fragments.like.MyLike;
import com.jy.beans.MyLikeBeans;

import java.util.ArrayList;
import java.util.List;

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.ViewHolder> {

    List<MyLikeBeans>  collectionDbBeanList= new ArrayList<>();
    Context context;
    public CollectionListAdapter(Context context) {

        this.context = context;
    }

    public void initData(List<MyLikeBeans>  collectionDbBeanList){

        this.collectionDbBeanList.clear();
        this.collectionDbBeanList.addAll(collectionDbBeanList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CollectionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collection,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final MyLikeBeans dbBean = collectionDbBeanList.get(i);

        Glide.with(context).load(dbBean.getImage()).into(viewHolder.imageView);
        viewHolder.from.setText(getTypeName(dbBean.getType()));

        viewHolder.title.setText(dbBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemClickListener!=null){

                    itemClickListener.onItemClick(dbBean.getType(),dbBean.getId());
                }
            }
        });
    }

    public String getTypeName(int type){

        switch (type){
            case 1:
                return "来自知乎";

            case 2:
                return "来自微信";

        }
        return "";
    }



    @Override
    public int getItemCount() {
        return collectionDbBeanList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title,from;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =  itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            from = itemView.findViewById(R.id.item_from);
        }
    }

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(int type, int id);
    }
}
