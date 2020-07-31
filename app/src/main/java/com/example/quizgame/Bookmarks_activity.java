package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.quizgame.Question_activity.FILE_NAME;
import static com.example.quizgame.Question_activity.KEY_NAME;


public class Bookmarks_activity extends AppCompatActivity {
private RecyclerView recyclerView;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private  List<Question_model>bookmarklist;
    private Gson gson;
/*
    public static final String FILE_NAME="QUIZ GAME";
    public static final String KEY_NAME="Question";
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks_activity);
        Toolbar toolbar=findViewById(R.id.toolbar);

         recyclerView=findViewById(R.id.recycleView);

        setSupportActionBar(toolbar);
         getSupportActionBar().setTitle("Bookmarks");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor=preferences.edit();
        gson=new Gson();
        getBookmarks();

        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayout);

        BooksMarksAdapter booksMarksAdapter=new BooksMarksAdapter(bookmarklist,getApplicationContext());
             recyclerView.setAdapter(booksMarksAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        storeBookmarks();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==android.R.id.home){
           finish();
       }
        return super.onOptionsItemSelected(item);

    }

    private void  getBookmarks(){
        String json =preferences.getString(KEY_NAME," ");

        Type type=new TypeToken<List<Question_model>>(){}.getType();

        bookmarklist=gson.fromJson(json,type);

        if (bookmarklist==null){
            bookmarklist=new ArrayList<>();
        }
    }

    private void storeBookmarks(){
        String json=gson.toJson(bookmarklist);

        editor.putString(KEY_NAME,json);
        editor.commit();
    }
}
