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
import com.example.quizgame.Question_activity;
import com.example.quizgame.R;

public class Computer extends AppCompatActivity {
    ListView listView;
    String [] name={"Computer Basic Question Set 1","Computer Basic Question Set 2","Ms-PowerPoint Question Set 3","Ms-Word Question Set 4","Ms-Excel Question Set 5"};
    Integer[] image={R.drawable.computer,R.drawable.computer,R.drawable.computer,R.drawable.computer,R.drawable.computer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Computer Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(Computer.this,name,image);
        listView.setAdapter(customlistAdapter);
        listView.setAdapter(customlistAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      String nam=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(Computer.this, Question_activity.class);
                intent.putExtra("computer",""+i);
                Toast.makeText(Computer.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
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
