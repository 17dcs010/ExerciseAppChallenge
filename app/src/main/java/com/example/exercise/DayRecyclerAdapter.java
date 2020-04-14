package com.example.exercise;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DayRecyclerAdapter extends RecyclerView.Adapter<DayRecyclerHolder> {
    ArrayList<ExerciseDay> exerciseDays;
    AlertDialog alerdialog;
    AlertDialog.Builder alerbuilder;
    Context context;
    int no=1;
    TextView notext;
    ImageButton add,remove;
    CardView start;
    public DayRecyclerAdapter(Context context,ArrayList<ExerciseDay>ex)
    {
        this.context=context;
        exerciseDays=ex;
    }
    @NonNull
    @Override
    public DayRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recyclersingle,parent,false);
        return new DayRecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DayRecyclerHolder holder, int position) {
holder.singleimage.setImageResource(exerciseDays.get(position).image);
holder.exercisename.setText(exerciseDays.get(position).exname);
holder.time.setText(exerciseDays.get(position).durartion+"");
holder.cardstart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

if(!isMyServiceRunning(MusicService.class))
        alerdialog.show();
else
{
    Intent i=new Intent(DayActivity.act,ExerciseMain.class);
    i.putExtra("arraylist",exerciseDays);
    DayActivity.act.startActivity(i);
}

    }
});
if(position==exerciseDays.size()-1) {
    holder.cardstart.setVisibility(View.VISIBLE);
    alerbuilder=new AlertDialog.Builder(DayActivity.act);
    View v=LayoutInflater.from(context).inflate(R.layout.setsalertdialog,null);
    alerbuilder.setView(v);
    notext=(TextView)v.findViewById(R.id.addno);
    add=(ImageButton) v.findViewById(R.id.add);
    remove=(ImageButton) v.findViewById(R.id.remove);
    start=(CardView)v.findViewById(R.id.cardstartdialog);
    start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            alerdialog.dismiss();

            Intent i=new Intent(DayActivity.act,GetReady.class);
            i.putExtra("arraylist",exerciseDays);
            i.putExtra("no",no);
            DayActivity.act.startActivity(i);
        }
    });
    alerdialog=alerbuilder.create();
    alerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            no++;
            notext.setText(no+"");
        }
    });
    remove.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(no==1)
                Toast.makeText(context,"At Least One Set need is Compulsary",Toast.LENGTH_LONG).show();
            else
            {
                no--;
                notext.setText(no+"");
            }
        }
    });
}
else
    holder.cardstart.setVisibility(View.GONE);
if(position==0)
    holder.today.setVisibility(View.VISIBLE);
else
    holder.today.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return exerciseDays.size();
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager)DayActivity.act.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
