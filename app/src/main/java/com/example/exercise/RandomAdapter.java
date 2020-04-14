package com.example.exercise;

import android.app.MediaRouteActionProvider;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RandomAdapter extends RecyclerView.Adapter<RandomHolder> {
Context c;
int itemclick=0;
ArrayList<ExerciseRandom> ex;
Map<Integer, ExerciseRandom>map;
RandomInterfaceClick interfaceClick;
    public RandomAdapter(Context context,ArrayList<ExerciseRandom>e,RandomInterfaceClick r) {
        super();
        c=context;
        map=new HashMap<>();
        ex=e;
        interfaceClick=r;
    }

    @NonNull
    @Override
    public RandomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.custom_random,parent,false);
        return new RandomHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomHolder holder, final int position) {
        holder.imageView.setImageResource(ex.get(position).image);
        holder.textView.setText(ex.get(position).exname);
        if(ex.get(position).sel==1)
            holder.cardview.setBackgroundColor(c.getResources().getColor(R.color.colorRed));
        else if(ex.get(position).sel==0)
            holder.cardview.setBackgroundColor(c.getResources().getColor(R.color.colorwhite));
        holder.cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(ex.get(position).sel==0)
                {
                    map.put(position,ex.get(position));
                    ex.get(position).sel=1;
                    itemclick++;
                    notifyItemChanged(position);
                    interfaceClick.RandomClick(itemclick,new ArrayList<ExerciseRandom>(map.values()));
                }
                else if(ex.get(position).sel==1)
                {
                    map.remove(position);
                    ex.get(position).sel=0;
                    itemclick--;
                    notifyItemChanged(position);
                    interfaceClick.RandomClick(itemclick,new ArrayList<ExerciseRandom>(map.values()));
                }
                return false;
            }
        });
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemclick>0)
                {
                    if(ex.get(position).sel==0)
                    {map.put(position,ex.get(position));
                        ex.get(position).sel=1;
                        itemclick++;
                        notifyItemChanged(position);
                        interfaceClick.RandomClick(itemclick,new ArrayList<ExerciseRandom>(map.values()));
                    }
                    else if(ex.get(position).sel==1)
                    {map.remove(position);
                        ex.get(position).sel=0;
                        itemclick--;
                        notifyItemChanged(position);
                        interfaceClick.RandomClick(itemclick,new ArrayList<ExerciseRandom>(map.values()));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ex.size();
    }

    public interface RandomInterfaceClick
    {
        public void RandomClick(int item, ArrayList<ExerciseRandom> random);
    }
}
