package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by peterdebrine on 12/6/16.
 */

public class SurveyActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "";
    ViewGroup dimensions;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_survey);
            init();
        }
        catch(IOException ie){
            Toast t = new Toast(getApplicationContext());
            t.setText("Could not read dimensions; fatal error");
            openMainNew();
        }
    }

    private void init() throws IOException {
        dimensions = (ViewGroup) findViewById(R.id.dimensions);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        Button general = new Button(getApplicationContext());
        SurveyMap m = new SurveyMap(getApplicationContext());
        ArrayList<String> dims = m.getDims();
        for (int i = 0; i < dims.size(); i++){
            // Dynamically add the dimensions as opposed to hard coding them in
        }
        general.setText("General");
        general.setClickable(true);
        general.setTextSize(26);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(0);
            }
        });
        Button water = new Button(getApplicationContext());
        water.setText("Water");
        water.setTextSize(26);
        water.setClickable(true);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(1);
            }
        });
        Button recycling = new Button(getApplicationContext());
        recycling.setTextSize(26);
        recycling.setText("Recycling");
        recycling.setClickable(true);
        recycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(2);
            }
        });
        dimensions.addView(general);
        dimensions.addView(water);
        dimensions.addView(recycling);

        }
    private void openSurvey(int i) {
        Intent intent = null;
        if (i == 0){
            intent = new Intent(this, GeneralActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "general");
        }
        if (i == 1){
            intent = new Intent(this, WaterActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "water");

        }
        if (i == 2){
            intent = new Intent(this, RecyclingActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "recycling");

        }
        if (intent!= null){
            startActivity(intent);
        }
        else openMainNew();
    }

    private void openMainNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
