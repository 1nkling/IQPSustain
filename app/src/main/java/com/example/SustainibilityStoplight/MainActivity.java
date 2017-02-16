package com.example.SustainibilityStoplight;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.SustainabilityStoplight.R;

public class MainActivity extends AppCompatActivity {

    TextView begin, learn, tips;

    void init(){
        begin = (Button) findViewById(R.id.begin);
        learn = (Button) findViewById(R.id.learn);
        tips = (Button) findViewById(R.id.tips);
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
        setContentView(R.layout.activity_main);
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
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }

    protected void openLearn(){
        Intent intent = new Intent(this, LearnMoreActivity.class);
        startActivity(intent);
    }


    public void openNewMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
