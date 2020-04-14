package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
MaterialCardView  cardView;
    Runnable r;
CircleImageView randomimage,dayimage;
ViewPager viewPager;
    Handler handler;
String arr[];
int npage=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
randomimage=findViewById(R.id.randomimage);
dayimage=findViewById(R.id.dayimage);
dayimage.setOnClickListener(this);
randomimage.setOnClickListener(this);
        cardView= (MaterialCardView) findViewById(R.id.card);
        viewPager=findViewById(R.id.photos_viewpager);
        arr=new String[4];
        arr[0]="android.resource://"+getPackageName()+"/"+R.drawable.side_bends_gif;
        arr[1]="android.resource://"+getPackageName()+"/"+R.drawable.bicycle_crunch_gif;
        arr[2]="android.resource://"+getPackageName()+"/"+R.drawable.deadlift_gif;
        arr[3]="android.resource://"+getPackageName()+"/"+R.drawable.tricep_gif;
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),arr);
        viewPager.setAdapter(pagerAdapter);
      r=  new Runnable() {
            @Override
            public void run() {
                if(npage==arr.length)
                    npage=0;
                viewPager.setCurrentItem(npage);
                npage++;
                new Handler().postDelayed(this,4000);
            }
        };
        //cardView.setBackgroundResource(R.drawable.cardstyle);
handler=new Handler();
handler.postDelayed(r,4000);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.randomimage)
        {
            Intent i=new Intent(getApplicationContext(),RandomActivity.class);
            startActivity(i);

        }
        else if(view.getId()==R.id.dayimage)
        {
            Intent i=new Intent(getApplicationContext(),DayActivity.class);
            i.putExtra("type","day");
            startActivity(i);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("mainac","stop");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("mainac","resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(r);
        Log.e("mainac","destroy");
    }
}
