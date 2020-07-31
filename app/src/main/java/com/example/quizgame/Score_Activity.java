package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score_Activity extends AppCompatActivity {
private TextView scored,total;
private Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_);

        scored=findViewById(R.id.score);
        total=findViewById(R.id.total);
        done=findViewById(R.id.done_btn);

        scored.setText(String.valueOf(getIntent().getIntExtra("score",0)));
        total.setText("OUT OF "+String.valueOf(getIntent().getIntExtra("total",0)));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent= new Intent(Score_Activity.this,Categories.class);
                startActivity(intent);
               */ finish();
            }
        });
    }
}
