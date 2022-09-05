package com.example.ryzencarrent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ryzencarrent.Modals.ReviewModal;
import com.example.ryzencarrent.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    Context context;
    List<ReviewModal> listdata;
    public ReviewAdapter(Context context, List<ReviewModal> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.review_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ReviewModal myListData = listdata.get(position);
        holder.r_name.setText(myListData.getUser_name());

        if (myListData.getReview()==null) {
            holder.r_review.setText("  - ");

        }else {
            holder.r_review.setText(myListData.getReview());

        }
        holder.r_rating.setRating(myListData.getRating());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView r_name, r_review;
        RatingBar r_rating;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.r_name = itemView.findViewById(R.id.r_name);
            this.r_review = itemView.findViewById(R.id.r_review);
            r_rating = itemView.findViewById(R.id.r_rating);
        }
    }
}
