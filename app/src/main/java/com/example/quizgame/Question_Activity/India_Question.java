package com.example.quizgame.Question_Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.Question_activity;
import com.example.quizgame.Question_model;
import com.example.quizgame.R;
import com.example.quizgame.Score_Activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class India_Question extends AppCompatActivity {
    public static final String FILE_NAME="QUIZ GAME";
    public static final String KEY_NAME="Question";
    private TextView questions, no_indicator;
    private FloatingActionButton bookmarks;
    private LinearLayout optioncontainer;
    private Button shareBtn, nextBtn;
    private int count = 0;
    private int position = 0;
    private List<Question_model> list;
    private int score = 0;
    private String[] question, option_a, option_b, option_c, option_d, answer;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private  List<Question_model>bookmarklist;
    private Gson gson;
    private int matchedQuestionPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questions = findViewById(R.id.questions);
        no_indicator = findViewById(R.id.no_indicator);
        bookmarks = findViewById(R.id.bookmark_btn);
        optioncontainer = findViewById(R.id.options_container);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);

        preferences=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor=preferences.edit();
        gson=new Gson();
        getBookmarks();
        bookmarks.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (modelMatch()){
                    bookmarklist.remove(matchedQuestionPosition);
                    bookmarks.setImageDrawable(getDrawable(R.drawable.bookmark_border));
                    Toast.makeText(India_Question.this, "Remove Successful in Bookmark", Toast.LENGTH_SHORT).show();

                }else {
                    bookmarklist.add(list.get(position));
                    bookmarks.setImageDrawable(getDrawable(R.drawable.ic_bookmarkbtn_black_24dp));
                    Toast.makeText(India_Question.this, "Add Successful in Bookmark", Toast.LENGTH_SHORT).show();

                }
            }
        });
        list = new ArrayList<>();

        india();
        for (int i = 0; i < 4; i++) {
            optioncontainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    checkAnswer(((Button) view));
                }

            });
        }
        playAnimation(questions, 0, list.get(position).getQuestion());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.7f);
                enabledoption(true);
                position++;
                if (position == list.size()) {
                    Intent scores=new Intent(India_Question.this, Score_Activity.class);
                    scores.putExtra("score",score);
                    scores.putExtra("total",list.size());
                    startActivity(scores);
                    finish();
                    return;
                }
                count = 0;
                playAnimation(questions, 0, list.get(position).getQuestion());
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body=list.get(position).getQuestion()+"\n"+
                        list.get(position).getOptions_a()+"\n"+
                        list.get(position).getOptions_b()+"\n"+
                        list.get(position).getOptions_c()+"\n"+
                        list.get(position).getOptions_d()+"\n"+
                        list.get(position).getCorrectAnswer();
                Intent share=new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT,"Quiz Game Challenge");
                share.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(share,"share via"));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        storeBookmarks();
    }

    private void playAnimation(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOptions_a();
                    } else if (count == 1) {

                        option = list.get(position).getOptions_b();
                    } else if (count == 2) {

                        option = list.get(position).getOptions_c();
                    } else if (count == 3) {

                        option = list.get(position).getOptions_d();
                    }
                    playAnimation(optioncontainer.getChildAt(count), 0, option);
                    count++;

                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        no_indicator.setText(position + 1 + "/" + list.size());
                        if (modelMatch()){

                            bookmarks.setImageDrawable(getDrawable(R.drawable.ic_bookmarkbtn_black_24dp));
                        }else {
                            bookmarks.setImageDrawable(getDrawable(R.drawable.bookmark_border));
                        }
                    } catch (ClassCastException e) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnimation(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption) {
        enabledoption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())) {
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        } else {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));

            Button correctOption = (Button) optioncontainer.findViewWithTag(list.get(position).getCorrectAnswer());

            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enabledoption(boolean enable) {
        for (int i = 0; i < 4; i++) {

            optioncontainer.getChildAt(i).setEnabled(true);
            if (enable) {
                optioncontainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));

            }
        }
    }
    private void  getBookmarks(){
        String json =preferences.getString(KEY_NAME," ");

        Type type=new TypeToken<List<Question_model>>(){}.getType();

        bookmarklist=gson.fromJson(json,type);

        if (bookmarklist==null){
            bookmarklist=new ArrayList<>();
        }
    }

    private boolean modelMatch(){
        boolean match=false;
        int i=0;
        for (Question_model model:bookmarklist){

            if (model.getQuestion().equals(list.get(position).getQuestion())
                    && model.getCorrectAnswer().equals(list.get(position).getCorrectAnswer()))
            {
                match=true;
                matchedQuestionPosition=i;
            }
            i++;
        }
        return match;
    }

    private void storeBookmarks(){
        String json=gson.toJson(bookmarklist);

        editor.putString(KEY_NAME,json);
        editor.commit();
    }

    private void india(){
        String india1=getIntent().getStringExtra("india");

        switch (india1){
            case "0":
                question=getResources().getStringArray(R.array.india_QuestionSet1);
                option_a=getResources().getStringArray(R.array.india_option_a1);
                option_b=getResources().getStringArray(R.array.india_option_b1);
                option_c=getResources().getStringArray(R.array.india_option_c1);
                option_d=getResources().getStringArray(R.array.india_option_d1);
                answer=getResources().getStringArray(R.array.india_answer_set1);
                for (int i=0;i<30;i++){
                    list.add(new Question_model(question[i],option_a[i],option_b[i],option_c[i],option_d[i],answer[i]));
                }
                break;
            case "1":
                question=getResources().getStringArray(R.array.india_QuestionSet2);
                option_a=getResources().getStringArray(R.array.india_option_a2);
                option_b=getResources().getStringArray(R.array.india_option_b2);
                option_c=getResources().getStringArray(R.array.india_option_c2);
                option_d=getResources().getStringArray(R.array.india_option_d2);
                answer=getResources().getStringArray(R.array.india_answer_set2);
                for (int i=0;i<30;i++){
                    list.add(new Question_model(question[i],option_a[i],option_b[i],option_c[i],option_d[i],answer[i]));
                }
                break;
            case "2":
                question=getResources().getStringArray(R.array.india_QuestionSet3);
                option_a=getResources().getStringArray(R.array.india_option_a3);
                option_b=getResources().getStringArray(R.array.india_option_b3);
                option_c=getResources().getStringArray(R.array.india_option_c3);
                option_d=getResources().getStringArray(R.array.india_option_d3);
                answer=getResources().getStringArray(R.array.india_answer_set3);
                for (int i=0;i<30;i++){
                    list.add(new Question_model(question[i],option_a[i],option_b[i],option_c[i],option_d[i],answer[i]));
                }
                break;
            case "3":
                question=getResources().getStringArray(R.array.india_QuestionSet4);
                option_a=getResources().getStringArray(R.array.india_option_a4);
                option_b=getResources().getStringArray(R.array.india_option_b4);
                option_c=getResources().getStringArray(R.array.india_option_c4);
                option_d=getResources().getStringArray(R.array.india_option_d4);
                answer=getResources().getStringArray(R.array.india_answer_set4);
                for (int i=0;i<30;i++){
                    list.add(new Question_model(question[i],option_a[i],option_b[i],option_c[i],option_d[i],answer[i]));
                }
                break;
            case "4":
                question=getResources().getStringArray(R.array.india_QuestionSet5);
                option_a=getResources().getStringArray(R.array.india_option_a5);
                option_b=getResources().getStringArray(R.array.india_option_b5);
                option_c=getResources().getStringArray(R.array.india_option_c5);
                option_d=getResources().getStringArray(R.array.india_option_d5);
                answer=getResources().getStringArray(R.array.india_answer_set5);
                for (int i=0;i<30;i++){
                    list.add(new Question_model(question[i],option_a[i],option_b[i],option_c[i],option_d[i],answer[i]));
                }
                break;


        }
    }

}

