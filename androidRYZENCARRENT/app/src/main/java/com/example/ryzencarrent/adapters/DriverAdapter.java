package com.example.ryzencarrent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.Modals.DriverModal;
import com.example.ryzencarrent.R;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.MyViewHolder> {
    Context context;
    List<DriverModal> listdata;
    int row_index=-1;
    ItemClickListener1 itemClickListener;
    public DriverAdapter(Context context, List<DriverModal> listdata,ItemClickListener1 itemClickListener) {
        this.listdata = listdata;
        this.context = context;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_available_driver, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DriverModal myListData = listdata.get(position);
        holder.txtDriverName.setText(myListData.getFullname());
        holder.txtDriverFee.setText("₹"+myListData.getDriver_fees());
        holder.txtDriverExp.setText(""+myListData.getExperience());
        holder.Dcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    row_index=position;
                    itemClickListener.onItemClickListener(myListData.getD_id(),myListData.getDriver_fees());
                    notifyDataSetChanged();
                }else {
                    itemClickListener.onItemClickListener(0,0);

                }
            }
        });
        if(row_index==position){
            holder.Dcheckbox.setChecked(true);
        }
        else
        {
            holder.Dcheckbox.setChecked(false);


        }


    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox Dcheckbox;
        public TextView txtDriverName,txtDriverFee,txtDriverExp;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtDriverName = itemView.findViewById(R.id.txtDriverName);
            this.txtDriverFee = itemView.findViewById(R.id.txtDriverFee);
            this.txtDriverExp = itemView.findViewById(R.id.txtDriverExp);
            this.Dcheckbox = itemView.findViewById(R.id.Dcheckbox);


        }
    }
    public interface ItemClickListener1 {
        void onItemClickListener(int s,int f);
    }

}
