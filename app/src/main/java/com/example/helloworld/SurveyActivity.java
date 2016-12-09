package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 12/6/16.
 */

public class SurveyActivity extends AppCompatActivity {
    ViewGroup dimensions;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        init();
    }

    private void init() {
        dimensions = (ViewGroup) findViewById(R.id.dimensions);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        TextView general = new TextView(getApplicationContext());
        general.setText("General");
        general.setClickable(true);
        general.setTextSize(26);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(0);
            }
        });
        TextView water = new TextView(getApplicationContext());
        water.setText("Water");
        water.setTextSize(26);
        water.setClickable(true);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(1);
            }
        });
        TextView recycling = new TextView(getApplicationContext());
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
        }
        if (i == 1){
            intent = new Intent(this, WaterActivity.class);
        }
        if (i == 2){
            intent = new Intent(this, RecyclingActivity.class);
        }
        if (intent!= null){
            startActivity(intent);
        }
        else openMainNew();
    }

    private void openMainNew(){
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }

}
