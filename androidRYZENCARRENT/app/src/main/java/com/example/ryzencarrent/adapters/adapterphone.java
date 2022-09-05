package com.example.ryzencarrent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.HomeScreen.HomeScreen;
import com.example.ryzencarrent.R;

import java.util.ArrayList;

public class adapterphone extends RecyclerView.Adapter<adapterphone.PhoneViewHold> {
    ArrayList<phonehelper> phoneLaocation;


    public adapterphone(ArrayList<phonehelper> phoneLaocation, HomeScreen homeScreen) {
        this.phoneLaocation = phoneLaocation;
    }

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        phonehelper phonehelper = phoneLaocation.get(position);
        holder.image.setImageResource(phonehelper.getImage());
    }

    @Override
    public int getItemCount() {
        return phoneLaocation.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public static class PhoneViewHold extends RecyclerView.ViewHolder {


        ImageView image;


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            //hooks

            image = itemView.findViewById(R.id.phone_image);

        }
    }
}
