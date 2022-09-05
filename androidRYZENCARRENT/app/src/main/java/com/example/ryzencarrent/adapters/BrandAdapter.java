package com.example.ryzencarrent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.Modals.BrandModal;
import com.example.ryzencarrent.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {
    Context context;
    List<BrandModal> listdata;
int row_index=-1;
    ItemClickListener itemClickListener;
    public BrandAdapter(Context context, List<BrandModal> listdata,ItemClickListener itemClickListener) {
        this.listdata = listdata;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.brand_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final BrandModal myListData = listdata.get(position);
        holder.txtBrand.setText(myListData.getBrand_name());
        holder.LLBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                itemClickListener.onItemClickListener(myListData.getBrand_name());
                notifyDataSetChanged();
            }
        });

        if(row_index==position){
            holder.LLBrand.setBackgroundResource(R.drawable.round_green_stroke);
            holder.ivSelect.setVisibility(View.VISIBLE);
//            holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.LLBrand.setBackgroundResource(R.drawable.round_brown_grey_stroke);
            holder.ivSelect.setVisibility(View.GONE);

//            holder.tv1.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout LLBrand;
        public TextView txtBrand;
ImageView ivSelect;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.LLBrand = itemView.findViewById(R.id.LLBrand);
            this.txtBrand = itemView.findViewById(R.id.txtBrand);
            this.ivSelect = itemView.findViewById(R.id.ivSelect);


        }
    }

   public interface ItemClickListener {
        void onItemClickListener(String s);
    }
}
