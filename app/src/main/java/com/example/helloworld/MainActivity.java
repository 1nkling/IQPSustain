package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Acer on 11/21/2016.
 */

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void waterQ(View view){
        Intent intent = new Intent(this, WaterActivity.class);
        startActivity(intent);
    }

    public void recycQ(View view) {
        Intent intent = new Intent(this, RecyclingActivity.class);
        startActivity(intent);
    }
}
