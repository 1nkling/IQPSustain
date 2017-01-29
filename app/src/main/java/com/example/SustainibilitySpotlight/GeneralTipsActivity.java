package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SustainibilitySpotlight.Struct.Question;
import com.example.SustainibilitySpotlight.Struct.QuestionAndResponse;
import com.example.SustainibilitySpotlight.Struct.Response;
import com.example.SustainibilitySpotlight.XML.XMLWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by peterdebrine on 12/13/16.
 */
public class GeneralTipsActivity extends AppCompatActivity {


    Button back;
    Button main;
    ViewGroup content;
    ArrayList<QuestionAndResponse> qAndA = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<Question>();
    private String name; // Its dimension

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

    // Populates this activity with its respective questions
    public void populate() {
        for (int i = 0; i < qAndA.size(); i++){
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
        name.setText("General Tips");
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
        tip1.setText("At every moment people often have things on that do not need to be," +
                "you should make a conscious effort to minimize this kind of waste. For example leaving the refrigerator open.");
        content.addView(tip1);
    }

    private void openMainTips() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }*/
}
