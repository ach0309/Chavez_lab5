package me.pgb.a2021_04_02_radioservice.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import me.pgb.a2021_04_02_radioservice.R;
import me.pgb.a2021_04_02_radioservice.models.MyViewHolder;
import me.pgb.a2021_04_02_radioservice.models.RadioStation;

public class RecyclerAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<RadioStation> stationList;

    public RecyclerAdapter(ArrayList<RadioStation> stationList){
        this.stationList = stationList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView name = ((MyViewHolder) holder).nameTxt;
        TextView genre = ((MyViewHolder) holder).genreTxt;
        TextView location = ((MyViewHolder) holder).locationTxt;
        TextView link = ((MyViewHolder) holder).urlTxt;

        name.setText(stationList.get(position).getName());
        genre.setText(stationList.get(position).getGenre());
        location.setText(stationList.get(position).getLocation());
        link.setText(stationList.get(position).getRadioStationURL());

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }
}
