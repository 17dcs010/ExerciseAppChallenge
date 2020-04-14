package com.example.exercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity  implements RandomAdapter.RandomInterfaceClick {
RecyclerView recyclerrandom;
int item;
MenuItem mitem;
    View actionView;
    ActionBar actionbar;
    Toolbar toolbar;
ArrayList<ExerciseRandom> selected;
ArrayList<ExerciseRandom>ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        ex=new ArrayList<>();
        ex.add(new ExerciseRandom("Crunches",1,R.drawable.crunches_gif,"android.resource://"+getPackageName()+"/"+R.raw.song1,0));
        ex.add(new ExerciseRandom("Scissor",3,R.drawable.scissor_exercise_gif,"android.resource://"+getPackageName()+"/"+R.raw.song4,0));
        ex.add(new ExerciseRandom("DeadLift",3,R.drawable.deadlift_gif,"android.resource://"+getPackageName()+"/"+R.raw.song2,0));
        ex.add(new ExerciseRandom("Triceps",2,R.drawable.tricep_gif,"android.resource://"+getPackageName()+"/"+R.raw.song3,0));
        ex.add(new ExerciseRandom("Pushups",2,R.drawable.pushups_gif,"android.resource://"+getPackageName()+"/"+R.raw.song4,0));
        ex.add(new ExerciseRandom("Side Bends",4,R.drawable.side_bends_gif,"android.resource://"+getPackageName()+"/"+R.raw.song5,0));
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
            actionbar.setTitle("Select Items");
            //actionView.setVisibility(View.GONE);
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorwhite));
            recyclerrandom = findViewById(R.id.recyclerrandom);
            LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            RandomAdapter randomAdapter = new RandomAdapter(getApplicationContext(), ex, this);
            recyclerrandom.setLayoutManager(manager);
            recyclerrandom.setAdapter(randomAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.tick);
menuItem.setVisible(false);
mitem=menuItem;
        actionView= menuItem.getActionView();
        //actionView.setVisibility(View.GONE);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // day activity call
RandomActivity.this.finish();
                Intent i=new Intent(getApplicationContext(),DayActivity.class);
                i.putExtra("type","random");
                i.putExtra("datae",selected);
                        startActivity(i);
            }
        });


return true;
        }

    @Override
    public void RandomClick(int item, ArrayList<ExerciseRandom> random) {
this.item=item;
selected=random;
Log.e("selected",selected.size()+"");
if(item>0)
{actionView.setVisibility(View.VISIBLE);
    mitem.setVisible(true);
    actionbar.setTitle(item +" Selected");
    // show action bar with tick
}
else
{actionView.setVisibility(View.GONE);
    mitem.setVisible(false);
    actionbar.setTitle("Select Items");
    // hide action bar
}
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
 class ExerciseRandom implements Serializable
{
    String exname;
    int durartion;
    int image;
    String music;
    int sel;
    public ExerciseRandom(String ex,int d,int i,String m,int s)
    {
        exname=ex;
        durartion=d;
        image=i;
        music=m;
        sel=s;
    }

}