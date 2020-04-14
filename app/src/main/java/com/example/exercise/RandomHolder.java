package com.example.exercise;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RandomHolder extends RecyclerView.ViewHolder {
    CardView cardview;
    TextView textView;
    ImageView imageView;
    public RandomHolder(@NonNull View itemView) {
        super(itemView);
        cardview=itemView.findViewById(R.id.gst_res_card);
        textView=itemView.findViewById(R.id.textviewrandom);
        imageView=itemView.findViewById(R.id.imageviewrandom);
    }
}
