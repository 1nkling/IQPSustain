package com.example.SustainibilityStoplight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SustainabilityStoplight.R;
import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by danso on 2/15/2017.
 */

public class Results extends AppCompatActivity {

    TextView results;
    
    Button back, submit;

    int answer_;

    void init(){
        int val = 0;
        int max = 1;
        int valL = 0;
        int maxL = 1;
        try {
            SurveyMap map = new SurveyMap(getApplicationContext());
            for (String dim: map.getDims()) {
                ArrayList<QuestionAndResponse> qrs = map.getQRs(dim);
                for (QuestionAndResponse qr : qrs) {
                    if (qr.getQuestion().isLowGood()){
                        valL += qr.getScore();
                        maxL += qr.getMax();
                    } else {
                        val += qr.getScore();
                        max += qr.getMax();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int answer = (100*val)/max;
        int answerL = (100*valL)/maxL;
        answerL = 100 - answerL;
        int finVal = (answer + answerL)/2;
        if (finVal < 0){
            finVal = -1 * finVal;
        }
        answer_ = finVal;
        results.setText(finVal + "%");
        TextView praise = (TextView) findViewById(R.id.praise);
        if (finVal > 66){
            praise.setText("Fantastic, keep it up, you can always get better!");
        }
        else if (finVal < 33){
            praise.setText("You really need to improve your sustainability habits");
        } else praise.setText("You can do better! Step your game up!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        results = (TextView) findViewById(R.id.score);
        back = (Button) findViewById(R.id.back);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitClicked();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
        init();
    }

    private void openMainNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void submitClicked() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Results");
        CharSequence text = Integer.toString(answer_);
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        String[] to = new String[1];
        to[0] = "psdebrine@wpi.edu"; // TODO use a different email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        try {
            Toast t = Toast.makeText(getApplicationContext(), "Please just send this email", Toast.LENGTH_LONG);
            t.show();
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast t = Toast.makeText(getApplicationContext(), "Something went wrong submitting", Toast.LENGTH_LONG);
            t.show();
            openMainNew();
        }
    }
}
