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
        TextView general = new TextView(getApplicationContext());
        general.setText("General");
        general.setClickable(true);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(0);
            }
        });
        TextView water = new TextView(getApplicationContext());
        water.setText("Water");
        water.setClickable(true);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSurvey(1);
            }
        });
        TextView recycling = new TextView(getApplicationContext());
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
        if (i == 0){

        }
        if (i == 1){
            Intent intent = new Intent(this, WaterActivity.class);
            startActivity(intent);
        }
        if (i == 2){
            Intent intent = new Intent(this, RecyclingActivity.class);
            startActivity(intent);
        }
    }

}
