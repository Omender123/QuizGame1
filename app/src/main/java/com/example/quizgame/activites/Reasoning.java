package com.example.quizgame.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quizgame.CustomlistAdapter;
import com.example.quizgame.Question_Activity.Invantion_Question;
import com.example.quizgame.Question_Activity.Resoning_Question;
import com.example.quizgame.R;

public class Reasoning extends AppCompatActivity {
    ListView listView;
    String [] name={"Reasoning Question Set 1","Reasoning Question Set 2","Reasoning Question Set 3","Reasoning Question Set 4","Reasoning Question Set 5"};
    Integer[] image={R.drawable.resoning,R.drawable.resoning,R.drawable.resoning,R.drawable.resoning,R.drawable.resoning};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasoning);

        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Reasoning Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(Reasoning.this,name,image);
        listView.setAdapter(customlistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nam=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(Reasoning.this, Resoning_Question.class);
                intent.putExtra("reasoning",""+i);
                Toast.makeText(Reasoning.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
