package com.example.exercise;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DayRecyclerHolder extends RecyclerView.ViewHolder {
    ImageView singleimage;
    TextView exercisename,time,today;
    CardView singlecard,cardstart;
    public DayRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        singleimage=itemView.findViewById(R.id.singleimage);
        exercisename=itemView.findViewById(R.id.exercisename);
        cardstart=itemView.findViewById(R.id.cardstart);
        today=itemView.findViewById(R.id.today);
        time=itemView.findViewById(R.id.time);
    }
}
