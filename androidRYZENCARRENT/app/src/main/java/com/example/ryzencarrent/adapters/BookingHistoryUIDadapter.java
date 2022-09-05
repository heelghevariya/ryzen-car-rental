package com.example.ryzencarrent.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ryzencarrent.Modals.BookingHistoryModal;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.config;
import com.example.ryzencarrent.drawermenu.BookedHistroyDetail;

import java.util.List;

public class BookingHistoryUIDadapter extends RecyclerView.Adapter<BookingHistoryUIDadapter.MyViewHolder> {
    Context context;
    List<BookingHistoryModal> listdata;

    public BookingHistoryUIDadapter(Context context, List<BookingHistoryModal> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_book_history, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final BookingHistoryModal myListData = listdata.get(position);
        String imgUrl = config.Email_Image_BASE_URL + myListData.getVimage1();
        holder.mTxtCarProducer.setText(myListData.getBrand_name());
        holder.mTxtCarVenue.setText(myListData.getVehicle_title());
        holder.mTxtFuelType.setText(myListData.getFuel_type());
        holder.mTxtmodalYear.setText("" + myListData.getModel_year());
        holder.mTxtSeats.setText(""+myListData.getSeating_capacity());
        holder.mTxtBookingNumber.setText("#" + myListData.getBooking_no());
        String date = myListData.getBooking_date().split(" ")[0];
        holder.bookingdate.setText(date);
        Glide.with(context).load(imgUrl).placeholder(R.drawable.carlogo).into(holder.mImgCar);
        if (myListData.getB_status()==0) {
            holder.status.setText("Pending");
            holder.status.setBackgroundResource(R.drawable.status_pending);

        }else  if (myListData.getB_status()==1) {
            holder.status.setText("Confirmed");
            holder.status.setBackgroundResource(R.drawable.status_confirm);

        }else  if (myListData.getB_status()==2) {
            holder.status.setText("Cancelled");
            holder.status.setBackgroundResource(R.drawable.status_cancle);


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BookedHistroyDetail.class);
                Bundle bundle = new Bundle();

                bundle.putString("getVehicle_title",myListData.getVehicle_title());
                bundle.putString("getFuel_type",myListData.getFuel_type());
                bundle.putString("getBrand_name",myListData.getBrand_name());
                bundle.putInt("getModel_year",myListData.getModel_year());
                bundle.putInt("getPriceperday",myListData.getPriceperday());
                bundle.putInt("getSeating_capacity",myListData.getSeating_capacity());
                bundle.putString("getVimage1",myListData.getVimage1());

                bundle.putString("getMobile_no",myListData.getMobile_no());
                bundle.putString("getAddress",myListData.getAddress());
                bundle.putString("getState",myListData.getState());
                bundle.putString("getCountry",myListData.getCountry());
                bundle.putString("getCadd",myListData.getCadd());
                    bundle.putString("getCno",myListData.getCno());
                bundle.putString("getCmail",myListData.getCmail());

                bundle.putInt("getBooking_no",myListData.getBooking_no());
                bundle.putString("getBooking_date",myListData.getBooking_date());
                bundle.putString("getFrom_date",myListData.getFrom_date());
                bundle.putString("getTo_date",myListData.getTo_date());
                bundle.putInt("getTotalnodays",myListData.getTotalnodays());
                bundle.putInt("getDriver_fees",myListData.getDriver_fees());
                bundle.putInt("getAmount_paid",myListData.getAmount_paid());
                bundle.putInt("getP_status",myListData.getP_status());
                bundle.putInt("getB_status",myListData.getB_status());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView status,mTxtBook,mTxtCarProducer, mTxtCarVenue, mTxtFuelType, mTxtmodalYear, mTxtSeats, mTxtBookingNumber,bookingdate;
        ImageView mImgCar;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mTxtCarProducer = itemView.findViewById(R.id.mTxtCarProducer);
            this.mTxtCarVenue = itemView.findViewById(R.id.mTxtCarVenue);
            this.mTxtFuelType = itemView.findViewById(R.id.mTxtFuelType);
            this.mTxtmodalYear = itemView.findViewById(R.id.mTxtmodalYear);
            this.mTxtSeats = itemView.findViewById(R.id.mTxtSeats);
            this.mTxtBookingNumber = itemView.findViewById(R.id.mTxtBookingNumber);
            this.bookingdate = itemView.findViewById(R.id.bookingdate);
            this.mTxtBook = itemView.findViewById(R.id.mTxtBook);
            this.mImgCar = itemView.findViewById(R.id.mImgCar);
            this.status = itemView.findViewById(R.id.status);

        }
    }
}
