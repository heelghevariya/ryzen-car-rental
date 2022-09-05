package com.example.ryzencarrent.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.R;
import com.example.ryzencarrent.VisitorPanel.VisitorHomeScreen;

import java.util.ArrayList;

public class adapterphone1 extends RecyclerView.Adapter<adapterphone1.PhoneViewHold> {
    ArrayList<phonehelper> phoneLaocation;


    public adapterphone1(ArrayList<phonehelper> phoneLaocation, VisitorHomeScreen visitorHomeScreen) {
        this.phoneLaocation = phoneLaocation;
    }

    @NonNull

    @Override
    public adapterphone1.PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new adapterphone1.PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adapterphone1.PhoneViewHold holder, int position) {


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

    public class PhoneViewHold extends RecyclerView.ViewHolder {


        ImageView image;


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            //hooks

            image = itemView.findViewById(R.id.phone_image);

        }
    }
}

