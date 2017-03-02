package com.example.SustainibilityStoplight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import static android.view.View.GONE;

/**
 * Created by peterdebrine on 12/13/16.
 */
public class GeneralTipsActivity extends AppCompatActivity {


    Button back;
    Button main;
    Button next;
    TextView score;
    ViewGroup content;
    SurveyMap map;
    ArrayList<QuestionAndResponse> qAndA = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<Question>();
    private String name; // Its dimension
    int val = 0;
    int max = 1;
    int valL = 0;
    int maxL = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Sets up the screen by initializing and setting the on click listeners, then calls init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_display);
        this.name = getName();
        TextView dimName = (TextView) findViewById(R.id.Name);
        dimName.setText(this.name);
        next = (Button) findViewById(R.id.next);
        Button results = (Button) findViewById(R.id.results);
        content = (ViewGroup) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        main = (Button) findViewById(R.id.main);
        score = (TextView) findViewById(R.id.score);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
            map = new SurveyMap(getApplicationContext());
            ArrayList<QuestionAndResponse> qrs = map.getQRs(name);
            for (QuestionAndResponse qr : qrs){
                if (qr.getQuestion().isLowGood()){
                    valL += qr.getScore();
                    maxL += qr.getMax();
                } else {
                    val += qr.getScore();
                    max += qr.getMax();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            map.getPrevDim(name);
        } catch (IOException e) {
            back.setVisibility(GONE);
        }
        try {
            map.getNextDim(name);
        } catch (IOException e) {
            next.setVisibility(GONE);
        }

        int answer = (100*val)/max;
        int answerL = (100*valL)/maxL;
        answerL = 100 - answerL;
        int finVal = (answer + answerL)/2;
        if (finVal < 0){
            finVal = -1 * finVal;
        }
        score.setText(finVal + "%");
        TextView praise = (TextView) findViewById(R.id.praise);
        if (finVal > 66){
            praise.setText("Fantastic, keep it up, you can always get better!");
        }
        else if (finVal < 33){
            praise.setText("You really need to improve your sustainability habits");
        } else praise.setText("You can do better! Step your game up!");

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
                tv.setPadding(5, 5, 5, 5);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                tv.setLayoutParams(params);
                tv.setText(qAndA.get(i).getQuestion().getRec());
                content.addView(tv);
            }
        }

    }

    private void next() throws IOException {
        String nextDim;
        try{
            nextDim = map.getNextDim(name);
        }
        catch(IOException ie){
            openMainNew();
            return;
        }
        Intent intent = new Intent(this, GeneralTipsActivity.class);
        intent.putExtra(SurveyActivity.EXTRA_MESSAGE, nextDim);
        startActivity(intent);
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
