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
import com.example.quizgame.Question_Activity.India_Question;
import com.example.quizgame.Question_activity;
import com.example.quizgame.R;

public class India extends AppCompatActivity {
    ListView listView;
    String [] name={"India G.K Question Set 1","India G.K Question Set 2","India G.K Question Set 3","India G.K Question Set 4","India G.K Question Set 5"};
    Integer[] image={R.drawable.india,R.drawable.india,R.drawable.india,R.drawable.india,R.drawable.india};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);
        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("India G.K Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(India.this,name,image);
        listView.setAdapter(customlistAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nam=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(India.this, India_Question.class);
                intent.putExtra("india",""+i);
                Toast.makeText(India.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
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
