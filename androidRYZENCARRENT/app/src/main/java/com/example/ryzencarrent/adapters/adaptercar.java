package com.example.ryzencarrent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.Modals.carhelper;
import com.example.ryzencarrent.R;

import java.util.ArrayList;

public class adaptercar extends RecyclerView.Adapter<adaptercar.PhoneViewHold>{
    ArrayList<carhelper> phoneLaocation;

    public adaptercar(ArrayList<carhelper> phoneLaocation, HomeScreen homeScreen) {
        this.phoneLaocation = phoneLaocation;
    }


    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whyryzen_car, parent, false);
        return new PhoneViewHold(view);
    }


    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


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
