package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class GetReady extends AppCompatActivity {
ProgressBar progressBar;
TextView progresstext;
Handler handler;
ArrayList<ExerciseDay>exerciseDays;
boolean active=true;
int count=10;
int set;
Runnable r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ready);
        exerciseDays=(ArrayList<ExerciseDay>) getIntent().getSerializableExtra("arraylist");
        set=getIntent().getIntExtra("no",1);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        handler=new Handler();
        r=new Runnable() {
            @Override
            public void run() {
                if(count==-1)
                {
                    handler.removeCallbacks(this);
                    handler=null;
                    Intent i1=new Intent(getApplicationContext(),MusicService.class);
                    i1.putExtra("arraylist",exerciseDays);
                    i1.putExtra("no",set);
                    startService(i1);
                    if(active)
                    { GetReady.this.finish();
                    Intent i=new Intent(getApplicationContext(),ExerciseMain.class);
                    i.putExtra("arraylist",exerciseDays);
startActivity(i);}


                }
                else {
                    progresstext.setText(count + "");
                    progressBar.setProgress((10 - count) * 10);

                    count--;
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(r,1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        active=true;
        if(count<=0)
        {
GetReady.this.finish();
            Intent i=new Intent(getApplicationContext(),ExerciseMain.class);
            i.putExtra("arraylist",exerciseDays);
            startActivity(i);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
active=false;
        Log.e("stop","stop is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null)
            handler.removeCallbacks(r);
        active=false;
        Log.e("stop","destroy is called");
    }
}
