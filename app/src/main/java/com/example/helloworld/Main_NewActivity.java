package com.example.helloworld;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main_NewActivity extends AppCompatActivity {

    TextView begin, learn, tips;

    void init(){
        begin = (TextView) findViewById(R.id.begin);
        learn = (TextView) findViewById(R.id.learn);
        tips = (TextView) findViewById(R.id.tips);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey();
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLearn();
            }
        });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTips();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        init();
    }

    protected void openSurvey(){
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);

    }

    protected void openMore(){

    }

    protected void openResults(){
        setContentView(R.layout.activity_results);
    }

    protected void openTips(){
        setContentView(R.layout.activity_tips);
    }

    protected void openLearn(){
        Intent intent = new Intent(this, LearnMoreActivity.class);
        startActivity(intent);
    }


    public void openNewMain(View view) {
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }
}
