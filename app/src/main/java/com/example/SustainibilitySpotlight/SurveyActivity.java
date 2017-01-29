package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import android.graphics.Color;

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
            Toast t = Toast.makeText(getApplicationContext(), "Could not read dimensions; fatal error", Toast.LENGTH_SHORT);
            t.show();
            ie.printStackTrace();
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
        SurveyMap m = new SurveyMap(getApplicationContext());
        ArrayList<String> dims = m.getDims();
        for (int i = 0; i < dims.size(); i++){
            Button temp = new Button(getApplicationContext());
            temp.setText(dims.get(i));
            temp.setClickable(true);
            temp.setTextSize(26);
            color(temp, i);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    openSurvey(b.getText().toString());
                }
            });
            temp.setPadding(20, 20, 20, 20);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            temp.setLayoutParams(params);
            dimensions.addView(temp);
        }
        }

    private void color(Button temp, int i) {
        if (i % 3 == 0){
            temp.setBackgroundColor(getColor(android.R.color.holo_red_light));
        }

        else if (i % 3 == 1){
            temp.setBackgroundColor(getColor(android.R.color.holo_orange_light));
        }

        else{
            temp.setBackgroundColor(getColor(android.R.color.holo_green_dark));
        }
    }

    private void openSurvey(String name) {
        Intent intent = new Intent(getApplicationContext(), AbstractSurvey.class);
        intent.putExtra(EXTRA_MESSAGE, name);
        startActivity(intent);

    }

    private void openMainNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
