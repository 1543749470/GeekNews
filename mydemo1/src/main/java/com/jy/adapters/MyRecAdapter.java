package com.jy.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydemo1.R;
import com.jy.bean.ListData;

import java.util.ArrayList;
import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.ViewHolder> {
    Context context;
    ArrayList<ListData> listData = new ArrayList<>();

    public MyRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rec_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rec_id.setText(listData.get(position).getId()+"");
        holder.rec_name.setText(listData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addData(List<ListData> listData) {
        if (this.listData!=null){
            this.listData.clear();
            this.listData.addAll(listData);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView rec_id;
        private TextView rec_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_id = itemView.findViewById(R.id.rec_id);
            rec_name = itemView.findViewById(R.id.rec_name);

        }
    }
}
