package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button start,bookmasrks;
SwitchCompat switchCompat;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.start);
        bookmasrks=findViewById(R.id.bookmarks);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  startActivity(new Intent(getApplicationContext(),Categories.class));
            }
        });

  bookmasrks.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent=new Intent(MainActivity.this,Bookmarks_activity.class);
          startActivity(intent);
      }
  });


    }
}
