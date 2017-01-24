package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by peterdebrine on 12/6/16.
 */

public class TipsActivity extends AppCompatActivity {
    ViewGroup stuff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        init();
    }

    void openMainNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void init() {
        stuff = (ViewGroup) findViewById(R.id.stuff);
        Button general = new Button(getApplicationContext());
        general.setText("General");
        general.setClickable(true);
        general.setTextSize(26);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTip(0);
            }
        });
        Button water = new Button(getApplicationContext());
        water.setText("Water");
        water.setTextSize(26);
        water.setClickable(true);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTip(1);
            }
        });
        Button recycling = new Button(getApplicationContext());
        recycling.setTextSize(26);
        recycling.setText("Recycling");
        recycling.setClickable(true);
        recycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTip(2);
            }
        });
        stuff.addView(general);
        stuff.addView(water);
        stuff.addView(recycling);

    }

    private void openTip(int i) {
        Intent intent = null;
        if (i == 0) {
            intent = new Intent(this, GeneralTipsActivity.class);
        }
        if (i == 1) {
            intent = new Intent(this, WaterTipsActivity.class);
        }
        if (i == 2) {
            intent = new Intent(this, RecyclingTipsActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        } else openMainNew();
    }


}