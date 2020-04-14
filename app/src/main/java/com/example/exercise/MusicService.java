package com.example.exercise;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    public ArrayList<ExerciseDay> m;
    int track=0;
    int breaks=0;
    int set;
    public  final IBinder binder = new LocalBinder();
    Timer t1,t2;
    MediaPlayer player;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","service started");
        m=(ArrayList<ExerciseDay>)intent.getSerializableExtra("arraylist");
set=intent.getIntExtra("no",1);
        player=new MediaPlayer();
        runMedia();
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void runMedia()
    {
        Thread t=new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("mse", m.get(0).music + "");
                    player.setDataSource(getApplicationContext(), Uri.parse(m.get(track).music));
                    Log.e("media1", Thread.currentThread().getName().toString());
                    player.prepareAsync();

                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                            breaks=0;
                            stopmedia(m.get(track).durartion);
                        }
                    });
                    //   player.start();
                } catch (Exception e) {
                    Log.e("message", e.getMessage());
                }
            }
        };
        t.start();

    }

    public void stopmedia(int d)
    {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.stop();
                player.reset();
                breaks=-1;
                restime();
            }
        },d*60*1000);
    }

    public void restime()
    {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(track>=m.size()-1&&set==1)
                {

                    player.release();
                    breaks=2;

                }
                else if(track>=m.size()-1&&set>1){
                    set--;
                    track=0;
                    runMedia();
                }
                else {
                    track++;
                    runMedia();
                }
            }
        },40000);
    }

    @Override
    public boolean stopService(Intent name) {
        Log.e("media","stop");
        return super.stopService(name);
    }

    public int getTrack()
    {
        return track;
    }
    public class LocalBinder extends Binder
    {
        MusicService getService()
        {
            return MusicService.this;
        }
    }
    public int getBreak()
    {
        return breaks;
    }
}
