package com.jy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geennews.R;
import com.jy.utils.Constants;
import com.jy.utils.GoldStatus;

public class GoldManagerAdapter extends RecyclerView.Adapter<GoldManagerAdapter.ViewHolder>{

    private LayoutInflater inflater;
    public GoldManagerAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.gold_manager_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        GoldStatus goldStatus = Constants.goldStatuses.get(i);
        viewHolder.tvType.setText(goldStatus.getTitle());
        viewHolder.scSwitch.setChecked(goldStatus.isSelected());
        viewHolder.scSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Constants.goldStatuses.get(viewHolder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constants.goldStatuses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvType;
        private SwitchCompat scSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_gold_manager_type);
            scSwitch = itemView.findViewById(R.id.sc_gold_manager_switch);
        }
    }
}
