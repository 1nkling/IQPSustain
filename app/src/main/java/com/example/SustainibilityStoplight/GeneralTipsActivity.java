package com.example.SustainibilityStoplight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SustainabilityStoplight.R;
import com.example.SustainibilityStoplight.Struct.Question;
import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by peterdebrine on 12/13/16.
 */
public class GeneralTipsActivity extends AppCompatActivity {


    Button back;
    Button main;
    TextView score;
    ViewGroup content;
    ArrayList<QuestionAndResponse> qAndA = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<Question>();
    private String name; // Its dimension
    int val = 0;
    int max = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Sets up the screen by initializing and setting the on click listeners, then calls init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_display);
        this.name = getName();
        TextView dimName = (TextView) findViewById(R.id.Name);
        dimName.setText(this.name);
        content = (ViewGroup) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        main = (Button) findViewById(R.id.main);
        score = (TextView) findViewById(R.id.score);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        try {
            SurveyMap map = new SurveyMap(getApplicationContext());
            ArrayList<QuestionAndResponse> qrs = map.getQRs(name);
            for (QuestionAndResponse qr : qrs){
                val += qr.getScore();
                max += qr.getMax();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        val = val*100;
        val = val/max;
        score.setText(val + "%");
        init();
    }

    protected String getName(){
        return getIntent().getStringExtra(SurveyActivity.EXTRA_MESSAGE);
    }

    protected void goBack(){
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);

    }

    protected void init(){
        try{
            // Gets a survey map (aka its list of questions)
            SurveyMap map = new SurveyMap(getApplicationContext());
            questions = map.getQuestions(this.name);
            qAndA = map.getQRs(name);
        }
        catch(IOException ie) {
            ie.printStackTrace();

        }
        catch (RuntimeException re){
            Toast t = Toast.makeText(getApplicationContext(), "Could not read something; fatal error", Toast.LENGTH_SHORT);
            t.show();
            re.printStackTrace();
            openMainNew();
        }
        populate();
    }

    //returns to the home menu
    private void openMainNew() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void toResults() {
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }

    // Populates this activity with its respective questions
    public void populate() {
        for (int i = 0; i < qAndA.size(); i++){
            if(qAndA.get(i).needsRec()) {
                TextView tv = new TextView(this);
                tv.setTextSize(26);
                tv.setPadding(20, 20, 20, 20);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                tv.setLayoutParams(params);
                tv.setText(qAndA.get(i).getQuestion().getRec());
                content.addView(tv);
            }
        }

    }

    /*LinearLayout content;
    View back;
    TextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_display);
        init();
    }

    private void init() {
        name = (TextView) findViewById(R.id.name);
        name.setResp("General Tips");
        content = (LinearLayout) findViewById(R.id.content);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainTips();
            }
        });
        TextView tip1 = new TextView(this);
        tip1.setTextSize(20);
        tip1.setResp("At every moment people often have things on that do not need to be," +
                "you should make a conscious effort to minimize this kind of waste. For example leaving the refrigerator open.");
        content.addView(tip1);
    }

    private void openMainTips() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }*/
}
