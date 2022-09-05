package com.example.ryzencarrent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.Modals.carhelper;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.VisitorPanel.VisitorHomeScreen;

import java.util.ArrayList;

public class adaptercar1  extends RecyclerView.Adapter<adaptercar1.PhoneViewHold>{
    ArrayList<carhelper> phoneLaocation;

    public adaptercar1(ArrayList<carhelper> phoneLaocation, VisitorHomeScreen visitorHomeScreen) {
        this.phoneLaocation = phoneLaocation;
    }


    public adaptercar1.PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whyryzen_car, parent, false);
        return new adaptercar1.PhoneViewHold(view);
    }


    public void onBindViewHolder(@NonNull adaptercar1.PhoneViewHold holder, int position) {


        carhelper carhelper = phoneLaocation.get(position);
        holder.image.setImageResource(carhelper.getImage());
    }


    public int getItemCount() {
        return phoneLaocation.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder {
        ImageView image;

        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.carinfo);

        }
    }
}

