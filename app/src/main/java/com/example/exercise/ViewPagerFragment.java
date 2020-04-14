package com.example.exercise;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ViewPagerFragment extends Fragment {
ImageView imageView;
String  id;

    public ViewPagerFragment(String id) {
        super();
        this.id=id;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.viewpager_fragment,container,false);
       imageView=v.findViewById(R.id.imageView);
        Glide.with(this).load(Uri.parse(id)).into(imageView);
        return v;
    }
}
