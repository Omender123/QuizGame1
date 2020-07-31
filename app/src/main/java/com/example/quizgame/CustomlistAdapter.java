package com.example.quizgame;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

public class CustomlistAdapter  extends ArrayAdapter {
public Activity activity;
private String [] name;
private Integer [] image;
    public CustomlistAdapter(@NonNull Activity activity, String [ ]name,Integer [] image) {
        super(activity,R.layout.customlist,name);

        this.activity=activity;
        this.name=name;
        this.image=image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=activity.getLayoutInflater().inflate(R.layout.customlist,null);
        ImageView imageView=view.findViewById(R.id.images);
        TextView textView=view.findViewById(R.id.name);

        imageView.setImageResource(image[position]);
        textView.setText(name[position]);
        return view;
    }
}
