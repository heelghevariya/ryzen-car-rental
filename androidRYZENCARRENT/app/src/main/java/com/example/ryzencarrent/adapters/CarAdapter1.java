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
import com.example.ryzencarrent.Modals.CarModal;
import com.example.ryzencarrent.R;
import com.example.ryzencarrent.VisitorPanel.VisiterVehicleDetails;
import com.example.ryzencarrent.config;

import java.util.List;

public class CarAdapter1 extends RecyclerView.Adapter<CarAdapter1.MyViewHolder> {
    Context context;
    List<CarModal> listdata;

    public CarAdapter1(Context context, List<CarModal> listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_carlist, parent, false);
        CarAdapter1.MyViewHolder viewHolder = new CarAdapter1.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter1.MyViewHolder holder, int position) {
        final CarModal myListData = listdata.get(position);
        String imgUrl = config.Email_Image_BASE_URL + myListData.getVimage1();
        holder.mTxtCarProducer.setText(myListData.getBrand_name());
        holder.mTxtCarVenue.setText(myListData.getVehicle_title());
        holder.mTxtFuelType.setText(myListData.getFuel_type());
        holder.mTxtmodalYear.setText("" + myListData.getModel_year());
        holder.mTxtSeats.setText(myListData.getSeating_capacity());
        holder.mTxtCarPrice.setText("₹" + myListData.getPriceperday());
        Glide.with(context).load(imgUrl).placeholder(R.drawable.carlogo).into(holder.mImgCar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VisiterVehicleDetails.class);
                Bundle bundle1 = new Bundle();

                bundle1.putString("getVehicle_title", myListData.getVehicle_title());
                bundle1.putString("getFuel_type", myListData.getFuel_type());
                bundle1.putString("getBrand_name", myListData.getBrand_name());
                bundle1.putInt("getModel_year", myListData.getModel_year());
                bundle1.putInt("getPriceperday", myListData.getPriceperday());
                bundle1.putString("getSeating_capacity", myListData.getSeating_capacity());
                bundle1.putString("getVehicle_detail", myListData.getVehicle_detail());
                bundle1.putInt("getAirconditioner", myListData.getAirconditioner());
                bundle1.putInt("getBrakeassiste", myListData.getBrakeassiste());
                bundle1.putInt("getPassengerairbage", myListData.getPassengerairbage());
                bundle1.putInt("getSmartgps", myListData.getSmartgps());
                bundle1.putInt("getAirfreshner", myListData.getAirfreshner());
                bundle1.putInt("getDashcam", myListData.getDashcam());
                bundle1.putInt("getChilddoorlock", myListData.getChilddoorlock());
                bundle1.putInt("getDriverairbage", myListData.getDriverairbage());
                bundle1.putInt("getPowerwindow", myListData.getPowerwindow());
                bundle1.putInt("getLEDdisplay", myListData.getLEDdisplay());
                bundle1.putInt("getAuxcable", myListData.getAuxcable());
                bundle1.putString("getVimage1", myListData.getVimage1());
                bundle1.putString("getVimage2", myListData.getVimage2());
                bundle1.putString("getVimage3", myListData.getVimage3());
                bundle1.putString("getVimage4", myListData.getVimage4());
                bundle1.putString("getVimage5", myListData.getVimage5());
                bundle1.putInt("getVehicle_id", myListData.getVehicle_id());
                intent.putExtras(bundle1);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtCarProducer, mTxtCarVenue, mTxtFuelType, mTxtmodalYear, mTxtSeats, mTxtCarPrice;
        ImageView mImgCar;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mTxtCarProducer = itemView.findViewById(R.id.mTxtCarProducer);
            this.mTxtCarVenue = itemView.findViewById(R.id.mTxtCarVenue);
            this.mTxtFuelType = itemView.findViewById(R.id.mTxtFuelType);
            this.mTxtmodalYear = itemView.findViewById(R.id.mTxtmodalYear);
            this.mTxtSeats = itemView.findViewById(R.id.mTxtSeats);
            this.mTxtCarPrice = itemView.findViewById(R.id.mTxtCarPrice);
            this.mImgCar = itemView.findViewById(R.id.mImgCar);

        }
    }
}
