package com.example.SustainibilitySpotlight;

import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

//Look into scale to measure numbers
//Look into save bundle state
//adaptiveView, NVC


public class WaterActivity extends AbstractSurvey {
    public final static String EXTRA_MESSAGE = "1";
    public final static String LIST = "";
    ArrayList<Integer> rating = new ArrayList<Integer>();
    int i = -1;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_survey);

        //TextView textView = (TextView) findViewById(R.id.text);
        //parses xml file with questions inside
        try {
            Intent intent = getIntent();
            XMLParser parser = new XMLParser();
            if(intent.getStringExtra(SurveyActivity.EXTRA_MESSAGE).matches("general"))
                Questions = parser.parse(getApplicationContext().getAssets().open("questions.xml"));
            else if(intent.getStringExtra(SurveyActivity.EXTRA_MESSAGE).matches("water"))
                Questions = parser.parse(getApplicationContext().getAssets().open("questions.xml"));
            else if(intent.getStringExtra(SurveyActivity.EXTRA_MESSAGE).matches("recycling"))
                Questions = parser.parse(getApplicationContext().getAssets().open("questions.xml"));
*//*
            String text="";
            for(Question question:Questions) {
                text+= "q : "+question.getQ()+" respType : "+question.getRespNum()+" Rec : "+question.getRec()+"\n";
            }

            textView.setText(text);*//*

        } catch (IOException e) {
            e.printStackTrace();
        }
        //dynamically generates Views based on parsed xml file
        for(int i = 0; i < Questions.size() ; i++) {
            //sets the values for question textView.
            TextView tv = new TextView(this);
            tv.setText(Questions.get(i).getQ());
            tv.setId(Questions.get(i).getId());

            //sets the values for response EditText.
            EditText et = new EditText(this);
            SharedPreferences waterPref = getSharedPreferences("waterAns", 0);
            if(waterPref.getLong(Integer.toString(i), -1) != -1){
                et.setText(Long.toString(waterPref.getLong(Integer.toString(i), -1)));
            }
            ViewGroup layout = (ViewGroup) findViewById(R.id.water_activity);
            layout.addView(tv);
            layout.addView(et);
            editTextList.add(et);

        }

        *//*SharedPreferences waterPref = getSharedPreferences("water", 0);
        rating.clear();
        rating.add(waterPref.getInt("0", 0));
        for(int x = 0; x < waterPref.getInt("0", 0); x++){
            rating.add(waterPref.getInt(Integer.toString(x+1),-1));
        }
        *//*

        View back = findViewById(R.id.main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
    }

    //returns to home menu
    private void openMainNew() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
    //passes responses to a display activity and saves them to memory
    public void sendMessage(View view) {
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //EditText editText2 = (EditText) findViewById(R.id.edit_message2);
        /*if((editText.getText().toString()).matches("")){
            Toast.makeText(this, "You did not select an option for q1", Toast.LENGTH_SHORT).show();
            return;
        }
        else if((editText2.getText().toString()).matches("")){
            Toast.makeText(this, "You did not select an option for q2", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(i == -1){
            Toast.makeText(this, "You did not select an option for q3", Toast.LENGTH_SHORT).show();
            return;
        }
*/
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        /*int num = Integer.parseInt(editText.getText().toString());
        String message = eval(num);
        int num2 = Integer.parseInt(editText2.getText().toString());
        String message2 = eval(num2);
        String fString = message.concat(message2);
        String ffString = fString.concat(conv(i));
        if(rating.get(0) == 0) {
            rating.set(0, 3);
            rating.add(eval2(num));
            rating.add(eval2(num2));
            rating.add(i);
        }
        else{
            rating.set(1, eval2(num));
            rating.set(2, eval2(num2));
            rating.set(3, i);
        }*/
        if(rating.size() < questions.size()) {
            for (int i = 0; i < editTextList.size(); i++) {
                if((editTextList.get(i).getText().toString()).matches("")){
                    Toast.makeText(this, "You did not select an option for q" + (i + 1), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isAlpha(editTextList.get(i).getText().toString())){
                    Toast.makeText(this, "You entered an invalid response to q" + (i + 1), Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = getSharedPreferences("waterAns", 0).edit();
                editor.putLong(Integer.toString(i), Math.round(Double.parseDouble(editTextList.get(i).getText().toString())));
                editor.commit();
                if(questions.get(i).isLowGood())
                    rating.add(eval(Double.parseDouble(editTextList.get(i).getText().toString()), questions.get(i).getMaxScore()));
                else
                    rating.add(eval2(Double.parseDouble(editTextList.get(i).getText().toString()), questions.get(i).getMaxScore()));
            }
        }
        else{
            for (int i = 0; i < editTextList.size(); i++) {
                if((editTextList.get(i).getText().toString()).matches("")){
                    Toast.makeText(this, "You did not select an option for q" + (i + 1), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(questions.get(i).isLowGood())
                    rating.set(i,eval(Double.parseDouble(editTextList.get(i).getText().toString()), questions.get(i).getMaxScore()));
                else
                    rating.set(i,eval2(Double.parseDouble(editTextList.get(i).getText().toString()), questions.get(i).getMaxScore()));
            }
        }
        intent.putExtra(EXTRA_MESSAGE, "water");
        intent.putExtra(LIST, rating);
        startActivity(intent);
    }

    //Checks results of previous responses
    public void checkStatus(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "water");
        SharedPreferences waterPref = getSharedPreferences("water", 0);
        rating.clear();
        //rating.add(waterPref.getInt("0", 0));
        for(int x = 0; x < questions.size(); x++){
            rating.add(waterPref.getInt(Integer.toString(x),-1));
        }
        startActivity(intent);
    }

    //unused; gets value from series of radio buttons
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.r_good:
                if (checked)
                    i = 0;
                break;
            case R.id.r_med:
                if (checked)
                    i = 1;
                break;
            case R.id.r_bad:
                if (checked)
                    i = 2;
                break;

        }
    }
//Scores user input based on a max score (low is "good")
    public int eval(double num, double maxScore) {
        double score = num / maxScore;
        if(score <= .3333)
            return 0;
        else if (score <= .6666)
            return 1;
        else
            return 2;
    }
    //Scores user input based on a max score (high is "good")
    public int eval2(double num, double maxScore) {
        double score = num / maxScore;
        if(score <= .3333)
            return 2;
        else if (score <= .6666)
            return 1;
        else
            return 0;
    }

    //Failed parsing attempt
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;
        String a = null, b = null, c = null, d = null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("q")) {
                            a = text;
                        } else if (name.equals("respType")) {
                            b = text;
                        } else if (name.equals("respNum")) {
                            c = text;
                        } else if (name.equals("rec")) {
                            d = text;
                        } else {
                        }
                        break;
                }
                event = myParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //TextView dynamicTextView = new TextView(this);
        TextView tv = (TextView) findViewById(R.id.test);
        //dynamicTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText(a + b + c + d + " hello");
    }

    public boolean isAlpha(String s) {
        char[] chars = s.toCharArray();
        for(char c:chars){
            if(Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

   /* @Override
    protected String setName() {
        return "water";
    }*/
}