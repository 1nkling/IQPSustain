package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Acer on 11/21/2016.
 */

public class WaterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
