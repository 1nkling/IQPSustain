package com.example.SustainibilityStoplight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.SustainabilityStoplight.R;

/**
 * Created by danso on 2/15/2017.
 */

public class Results extends AppCompatActivity {

    TextView begin, learn, tips;

    void init(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        init();
    }
}
