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
import com.example.quizgame.Question_Activity.World_Questions;
import com.example.quizgame.R;

public class World extends AppCompatActivity {
    ListView listView;
    String [] name={"World G.K Question Set 1","World G.K Question Set 2","World G.K Question Set 3","World G.K Question Set 4","World G.K Question Set 5"};
    Integer[] image={R.drawable.world,R.drawable.world,R.drawable.world,R.drawable.world,R.drawable.world};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("World G.K Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(World.this,name,image);
        listView.setAdapter(customlistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nam=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(World.this, World_Questions.class);
                intent.putExtra("world",""+i);
                Toast.makeText(World.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
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
