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
import com.example.quizgame.R;

public class Invantion extends AppCompatActivity {
    ListView listView;
    String [] name={"Invention Question Set 1","Invention Question Set 2","Invention Question Set 3","Invention Question Set 4","Invention Question Set 5"};
    Integer[] image={R.drawable.invantion,R.drawable.invantion,R.drawable.invantion,R.drawable.invantion,R.drawable.invantion,R.drawable.invantion};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invantion);

        listView=findViewById(R.id.listView);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Invention Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomlistAdapter customlistAdapter=new CustomlistAdapter(Invantion.this,name,image);
        listView.setAdapter(customlistAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nam=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(Invantion.this, Invantion_Question.class);
                intent.putExtra("invention",""+i);
                Toast.makeText(Invantion.this, "Welcome to "+nam, Toast.LENGTH_SHORT).show();
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
