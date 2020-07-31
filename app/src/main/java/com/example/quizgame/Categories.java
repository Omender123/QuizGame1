package com.example.quizgame;

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

import com.example.quizgame.activites.Computer;
import com.example.quizgame.activites.India;
import com.example.quizgame.activites.Invantion;
import com.example.quizgame.activites.Mathematics;
import com.example.quizgame.activites.Reasoning;
import com.example.quizgame.activites.World;


public class Categories extends AppCompatActivity {
ListView listView;
String [] name={"Computer Quiz","India G.K Quiz","World G.K Quiz","Invention","Reasoning Quiz","Mathematics Quiz"};
Integer[] image={R.drawable.computer,R.drawable.india,R.drawable.world,R.drawable.invantion,R.drawable.resoning,R.drawable.math};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(Categories.this,name,image);

        listView.setAdapter(customlistAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             String nam=listView.getItemAtPosition(i).toString();

              switch (i){
                  case 0:
                      Intent intent=new Intent(getApplicationContext(), Computer.class);
                      startActivity(intent);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                      break;

                  case 1:
                      Intent intent1=new Intent(getApplicationContext(), India.class);
                      startActivity(intent1);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();

                      break;

                  case 2:
                      Intent intent2=new Intent(getApplicationContext(), World.class);
                      startActivity(intent2);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                      break;

                  case 3:
                      Intent intent3=new Intent(getApplicationContext(), Invantion.class);
                      startActivity(intent3);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                      break;

                  case 4:
                      Intent intent4=new Intent(getApplicationContext(), Reasoning.class);
                      startActivity(intent4);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                      break;

                  case 5:
                      Intent intent5=new Intent(getApplicationContext(), Mathematics.class);
                      Toast.makeText(Categories.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
                      startActivity(intent5);
                      break;

              }
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
