package com.example.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.MainActivity;
import com.example.helloworld.R;
import com.example.helloworld.Struct.Question;
import com.example.helloworld.SurveyActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterdebrine on 12/15/16.
 */

public abstract class AbstractSurvey extends AppCompatActivity {

    Button back;
    Button main;
    ViewGroup content;
    Button submit;
    Button status;
    ArrayList<Question> questions = new ArrayList<Question>();
    List<EditText> editTextList = new ArrayList<EditText>();
    private String name;

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

    private void saveAnswers() {

    }

    protected void init(){
        // Currently only calls this function but might do more or move stuff here not sure what I want to do
        populate();
    }

    //returns to the home menu
    private void openMainNew() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Populates this activity with its respective questions
    public void populate() {
        try{
            // Gets a survey map (aka its list of questions)
            SurveyMap map = new SurveyMap(getApplicationContext());
            questions = map.getQuestions(this.name);
            // Puts the questions on the screen and their respective edit texts
            for(int i = 0; i < questions.size() ; i++) {
                TextView tv = new TextView(this);
                tv.setText(questions.get(i).getQ());
                tv.setId(questions.get(i).getId());

                EditText et = new EditText(this);
                SharedPreferences waterPref = getSharedPreferences("waterAns", 0);
                if(waterPref.getLong(Integer.toString(i), -1) != -1){
                    et.setText(Long.toString(waterPref.getLong(Integer.toString(i), -1)));
                }
                content = (ViewGroup) findViewById(R.id.content);
                content.addView(tv);
                content.addView(et);
                editTextList.add(et);

            }

        }
        catch(IOException ie) {
            ie.printStackTrace();

        }
        catch (RuntimeException re){
            Toast t = new Toast(getApplicationContext());
            t.setText("Dimension does not contain questions");
            t.show();
            openMainNew();
        }

    }



}
