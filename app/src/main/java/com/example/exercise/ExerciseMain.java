package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ExerciseMain extends AppCompatActivity {
MusicService mservice;
boolean  mBound;
Handler handler;
Runnable r;
int breaks;
int track;
int ptrack=-1;
boolean active=true;
ArrayList<ExerciseDay>exd;
ImageView imageview;
TextView extext,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_main);
        imageview=findViewById(R.id.imageview);
        extext=findViewById(R.id.extext);
        status=findViewById(R.id.status);
        handler=new Handler();
        exd=(ArrayList<ExerciseDay>) getIntent().getSerializableExtra("arraylist");

        r=new Runnable() {
            @Override
            public void run() {
                if(mBound)
                {
                track=mservice.getTrack();
                breaks=mservice.getBreak();
                if(breaks==0)
                {
                    status.setVisibility(View.GONE);
                    imageview.setVisibility(View.VISIBLE);
                    extext.setVisibility(View.VISIBLE);
                }
                else if(breaks==-1)
                {
                    imageview.setVisibility(View.GONE);
                    extext.setVisibility(View.GONE);
                    status.setVisibility(View.VISIBLE);
                    status.setText("BREAK!!");


                }
                else if(breaks==2)
                {
                    imageview.setVisibility(View.GONE);
                    extext.setVisibility(View.GONE);
                    status.setVisibility(View.VISIBLE);
                    status.setText("ALL DONE!!");
                    handler.removeCallbacks(r);
                    stopService(new Intent(getApplicationContext(),MusicService.class));
                }
                if (track!=ptrack) {
                    ptrack = track;
                    if(track<exd.size())
                    {
                        imageview.setVisibility(View.VISIBLE);
                        extext.setVisibility(View.VISIBLE);
                        status.setVisibility(View.GONE);

                        Glide.with(getApplicationContext()).load(Uri.parse("android.resource://"+getPackageName()+"/"+exd.get(track).image)).into(imageview);
                extext.setText(exd.get(track).exname);
                }}

                }
                handler.postDelayed(this,3000);
            }
        };
        handler.postDelayed(r,3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        if(!active)
        {
            r=new Runnable() {
                @Override
                public void run() {
                    if(mBound)
                    {
                        track=mservice.getTrack();
                        if (track!=ptrack) {
                            ptrack = track;
                            if(track<exd.size()) {
                                Glide.with(getApplicationContext()).load(Uri.parse("android.resource://" + getPackageName() + "/" + exd.get(track).image)).into(imageview);
                                extext.setText(exd.get(track).exname);
                            }
                        }

                    }
                    handler.postDelayed(this,3000);
                }
            };
            handler.postDelayed(r,3000);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
        active=false;
        handler.removeCallbacks(r);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mservice=binder.getService();
            mBound = true;
              Glide.with(getApplicationContext()).load(Uri.parse("android.resource://"+getPackageName()+"/"+exd.get(mservice.getTrack()).image)).into(imageview);
            extext.setText(exd.get(mservice.getTrack()).exname);





        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            if(handler!=null)
                handler.removeCallbacks(r);
            Log.e("handler","re");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(r);
    }

}
