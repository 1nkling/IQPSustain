package com.example.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int[] ids = {R.id.water1, R.id.water2, R.id.water3};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        ArrayList<Integer> data = new ArrayList<Integer>();
        if(intent.getStringExtra(WaterActivity.EXTRA_MESSAGE).matches("water")){
            data = waterData();
            for(int x = 0; x < data.get(0); x++){
                if(data.get(x+1) > 1){
                    TextView tv = (TextView) findViewById(ids[x]);
                    tv.setVisibility(View.VISIBLE);
                }
            }
        }

        TextView intTextView = (TextView)findViewById(R.id.tv1);
        for(int i = 0; i < data.size(); i++){
            intTextView.setText(intTextView.getText() + "\n" + Integer.toString(data.get(i)));
        }
        String message = intent.getStringExtra(WaterActivity.EXTRA_MESSAGE);
        message = "a";
        message = "v";
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }

    public ArrayList<Integer> waterData(){
        Intent intent = getIntent();
        ArrayList<Integer> rating = intent.getIntegerArrayListExtra(WaterActivity.LIST);
        SharedPreferences waterPref = getSharedPreferences("water", 0);
        if(rating == null){
            rating = new ArrayList<Integer>();
            rating.add(waterPref.getInt("0", 0));
            for(int x = 0; x < rating.get(0); x++){
                rating.add(waterPref.getInt(Integer.toString(x+1),-1));
            }
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences("water", 0).edit();
            for(int i = 0; i < rating.size(); i++) {
                editor.putInt(Integer.toString(i), rating.get(i));
                //intTextView.setText(intTextView.getText() + Integer.toString(rating.get(i)));
            }
            editor.commit();
        }
        return rating;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void goHome(View view){
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }
}
