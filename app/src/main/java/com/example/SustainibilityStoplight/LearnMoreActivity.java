package com.example.SustainibilityStoplight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.SustainabilityStoplight.R;

/**
 * Created by peterdebrine on 12/6/16.
 */

public class LearnMoreActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        View back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
    }

    void openMainNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
