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
    ViewGroup content;
    Button submit;
    Button status;
    ArrayList<QuestionAndResponse> qAndA = new ArrayList();
    ArrayList<Question> questions = new ArrayList<Question>();
    List<EditText> editTextList = new ArrayList<EditText>(); // Their answers
    private String name; // Its dimension

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Sets up the screen by initializing and setting the on click listeners, then calls init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_survey);
        this.name = setName();
        TextView dimName = (TextView) findViewById(R.id.Name);
        dimName.setText(this.name);
        content = (ViewGroup) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.send);
        status = (Button) findViewById(R.id.status);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        main = (Button) findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        init();
    }

    protected String setName(){
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
            SharedPreferences sustPref = getSharedPreferences("SustSpotlight", 0);
            if (sustPref.getBoolean(name, false) && sustPref.getBoolean("saved", false)){
                ArrayList<Response> resps = map.getResponses(name);
                int i = 0, j = 0;
                if (resps.size() == questions.size()){
                    // puts them in the array in order of the new QandR's id number
                    for (; i < questions.size(); i++){
                        for (; j < resps.size(); j++){
                            if (questions.get(i).getId() == resps.get(j).getId()) {
                                QuestionAndResponse temp = new QuestionAndResponse(questions.get(i), resps.get(j));
                                qAndA.add(temp.getId(), temp);
                                break;
                            }
                        }
                    }
                }
                else { // The survey was not totally filled out

                    ArrayList<Integer> filled = new ArrayList<>();

                    // puts them in the array in order of the new QandR's id number
                    for (; i < questions.size(); i++){
                        for (; j < resps.size(); j++){
                            if (questions.get(i).getId() == resps.get(j).getId()) {
                                QuestionAndResponse temp = new QuestionAndResponse(questions.get(i), resps.get(j));
                                qAndA.add(temp.getId(), temp);
                                filled.add(temp.getId());
                                break;
                            }
                        }
                        if (qAndA.size() == resps.size()){
                            break;
                        }
                    }

                    for (i = 0; i < questions.size(); i++){
                        if (filled.contains(new Integer(i)));
                        else {
                            Response resp = new Response(i, name, "", getApplicationContext());
                            QuestionAndResponse temp = new QuestionAndResponse(questions.get(i), resp);
                            qAndA.add(i, temp);
                        }
                    }
                }
            }
            else {
                int i;
                for (i = 0; i < questions.size(); i++){
                    Response resp = new Response(i, name, "", getApplicationContext());
                    QuestionAndResponse temp = new QuestionAndResponse(questions.get(i), resp);
                    qAndA.add(i, temp);
                }
            }
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
            content.addView(qAndA.get(i).getContent());
        }

    }

    private void updateResps(){
        int i = 0;
        for (; i < content.getChildCount(); i++){
            LinearLayout qAndR = (LinearLayout) content.getChildAt(i);
            EditText et = (EditText) qAndR.getChildAt(1);
            String text = et.getText().toString();
            qAndA.get(i).getResp().updateText(text);

        }
    }



}
