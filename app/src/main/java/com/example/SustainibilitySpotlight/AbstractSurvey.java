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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SustainibilitySpotlight.Struct.Question;
import com.example.SustainibilitySpotlight.Struct.QuestionAndResponse;
import com.example.SustainibilitySpotlight.Struct.Response;
import com.example.SustainibilitySpotlight.XML.XMLWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterdebrine on 12/15/16.
 */

public class AbstractSurvey extends AppCompatActivity {

    Button back;
    Button main;
    Button next;
    ViewGroup content;
    Button submit;
    Button status;
    ArrayList<QuestionAndResponse> qAndA = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<EditText> editTextList = new ArrayList<>();
    private String name; // Its dimension

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Sets up the screen by initializing and setting the on click listeners, then calls init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_survey);
        this.name = getName();
        TextView dimName = (TextView) findViewById(R.id.Name);
        dimName.setText(this.name);
        content = (ViewGroup) findViewById(R.id.content);
        next = (Button) findViewById(R.id.next);
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
        init();
    }

    private void next() throws IOException {
        SurveyMap map = new SurveyMap(getApplicationContext());
        String nextDim = "";
        try{
            nextDim = map.getNextDim(name);
        }
        catch(IOException ie){
            openMainNew();
            return;
        }
        saveAnswers();
        Intent intent = new Intent(this, AbstractSurvey.class);
        intent.putExtra(SurveyActivity.EXTRA_MESSAGE, nextDim);
        startActivity(intent);
    }

    protected String getName(){
        return getIntent().getStringExtra(SurveyActivity.EXTRA_MESSAGE);
    }

    protected void goBack(){
        this.saveAnswers();
        Intent intent = new Intent(this, SurveyActivity.class);
        startActivity(intent);
    }

    //TODO
    private void saveAnswers() {
        updateResps();
        ArrayList<Response> temp = new ArrayList<>();
        for (int i = 0; i < qAndA.size(); i++){
            temp.add(i, qAndA.get(i).getResp());
        }
        XMLWriter writer = new XMLWriter();
        try {
            writer.writeResponses(temp, getApplicationContext(), this.name);
            SharedPreferences.Editor editor = getSharedPreferences("SustSpotlight", 0).edit();
            editor.putBoolean(name, true);
            editor.putBoolean("saved", true);
            editor.commit();
            Log.d("saveAnswers","Saved answers");
            Log.d("saveAnswers",temp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        saveAnswers();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Populates this activity with its respective questions
    public void populate() {
        for (int i = 0; i < qAndA.size(); i++){
            content.addView(qAndA.get(i).getContent());
        }

    }

    private void updateResps(){
        int i = 0;
        for (; i < content.getChildCount(); i++){
            LinearLayout qAndR = (LinearLayout) content.getChildAt(i);
            SeekBar bar = (SeekBar) qAndR.getChildAt(1);
            int resp = bar.getProgress();
            qAndA.get(i).getResp().setResp(resp);

        }
    }



}
