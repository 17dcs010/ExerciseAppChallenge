package com.example.exercise;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayActivity extends AppCompatActivity {
Toolbar toolbar;
public static DayActivity act;
RecyclerView recyclerview;
ArrayList<ExerciseDay>exerciseDays;
ArrayList<ExerciseRandom> exrandom;
ActionBar actionbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        act = this;
        recyclerview = findViewById(R.id.recyclerview);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        getWindow().setStatusBarColor(R.color.colorRed);
        if (getIntent().getStringExtra("type").equals("random")) {
            exerciseDays = new ArrayList<>();
            actionbar.setTitle("RANDOM");
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorwhite));

            exrandom = (ArrayList<ExerciseRandom>) getIntent().getSerializableExtra("datae");
            for(int i=0;i<exrandom.size();i++)
             exerciseDays.add(new ExerciseDay(exrandom.get(i).exname,exrandom.get(i).durartion,exrandom.get(i).image,exrandom.get(i).music));

                DayRecyclerAdapter adapter = new DayRecyclerAdapter(getApplicationContext(), exerciseDays);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                recyclerview.setLayoutManager(layoutManager);
                recyclerview.setAdapter(adapter);


        }

       else {
        exerciseDays = new ArrayList<>();

        actionbar.setTitle("DAY");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorwhite));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        actionbar.setTitle(dayOfTheWeek);
        if (dayOfTheWeek.equals("Monday")) {
            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));


        } else if (dayOfTheWeek.equals("Tuesday")) {
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Side Bends", 4, R.drawable.side_bends_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
        } else if (dayOfTheWeek.equals("Wednesday")) {

            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Side Bends", 4, R.drawable.side_bends_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
        } else if (dayOfTheWeek.equals("Thursday")) {

            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
            exerciseDays.add(new ExerciseDay("Side Bends", 4, R.drawable.side_bends_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
        } else if (dayOfTheWeek.equals("Friday")) {

            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));

        } else if (dayOfTheWeek.equals("Saturday")) {
            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Side Bends", 4, R.drawable.side_bends_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));

        } else if (dayOfTheWeek.equals("Sunday")) {
            exerciseDays.add(new ExerciseDay("DeadLift", 3, R.drawable.deadlift_gif, "android.resource://" + getPackageName() + "/" + R.raw.song1));
            exerciseDays.add(new ExerciseDay("Side Bends", 4, R.drawable.side_bends_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));
            exerciseDays.add(new ExerciseDay("Triceps", 2, R.drawable.tricep_gif, "android.resource://" + getPackageName() + "/" + R.raw.song3));
            exerciseDays.add(new ExerciseDay("Pushups", 2, R.drawable.pushups_gif, "android.resource://" + getPackageName() + "/" + R.raw.song4));
            exerciseDays.add(new ExerciseDay("Crunches", 2, R.drawable.crunches_gif, "android.resource://" + getPackageName() + "/" + R.raw.song5));
            exerciseDays.add(new ExerciseDay("Scissor", 3, R.drawable.scissor_exercise_gif, "android.resource://" + getPackageName() + "/" + R.raw.song2));

        }


        DayRecyclerAdapter adapter = new DayRecyclerAdapter(getApplicationContext(), exerciseDays);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
    }}
    }


class ExerciseDay implements Serializable
{
    String exname;
    int durartion;
    int image;
    String music;
    public ExerciseDay(String ex,int d,int i,String m)
    {
        exname=ex;
        durartion=d;
        image=i;
        music=m;
    }

}
