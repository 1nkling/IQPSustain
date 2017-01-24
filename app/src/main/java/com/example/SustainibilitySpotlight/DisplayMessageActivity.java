package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.SustainibilitySpotlight.Struct.Question;
import com.example.SustainibilitySpotlight.XML.XMLParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// Toast of percentage complete
public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int[] ids = {R.id.water1, R.id.water2, R.id.water3};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        ArrayList<Integer> rating = intent.getIntegerArrayListExtra(WaterActivity.LIST);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        List<Question> Questions = null;

        /*ArrayList<Integer> data = new ArrayList<Integer>();
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
        */

        try {
            XMLParser parser = new XMLParser();
            if(intent.getStringExtra(WaterActivity.EXTRA_MESSAGE).matches("water")) {
                Questions = parser.parse(getApplicationContext().getAssets().open("questions.xml"));
                //Saving current responses to memory
                SharedPreferences.Editor editor = getSharedPreferences("water", 0).edit();
                SharedPreferences waterPref = getSharedPreferences("water", 0);
                if(rating == null) {
                    rating = new ArrayList<Integer>();
                    for (int x = 0; x < Questions.size(); x++) {
                        rating.add(waterPref.getInt(Integer.toString(x), -1));
                    }
                }
                else {
                    for (int i = 0; i < rating.size(); i++) {
                        editor.putInt(Integer.toString(i), rating.get(i));
                        //intTextView.setText(intTextView.getText() + Integer.toString(rating.get(i)));
                    }
                    editor.commit();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView textView = new TextView(this);
        textView.setText("");
        for(int i: rating){
            textView.setText(textView.getText() + "\n" + Integer.toString(i));
        }
        layout.addView(textView);

        for(int i = 0; i < Questions.size() ; i++) {
            TextView tv = new TextView(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            if(rating.get(i) > 1)
                tv.setText("\n" + Questions.get(i).getRec() + "\n");
            layout.addView(tv);

        }
    }

    /*public ArrayList<Integer> waterData(){
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
    }*/

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}