package com.example.helloworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.Struct.Question;
import com.example.helloworld.XMLParser.XMLParser;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Look into scale to measure numbers
//Look into save bundle state
//adaptiveView, NVC


public class WaterActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "1";
    public final static String LIST = "";
    ArrayList<Integer> rating = new ArrayList<Integer>();
    int i = -1;
    List<Question> Questions = null;
    List<EditText> editTextList = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        //TextView textView = (TextView) findViewById(R.id.text);
        try {
            XMLParser parser = new XMLParser();
            Questions = parser.parse(getApplicationContext().getAssets().open("questions.xml"));
/*
            String text="";
            for(Question question:Questions) {
                text+= "q : "+question.getQ()+" respType : "+question.getRespNum()+" Rec : "+question.getRec()+"\n";
            }

            textView.setText(text);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < Questions.size() ; i++) {
            TextView tv = new TextView(this);
            tv.setText(Questions.get(i).getQ());
            tv.setId(Questions.get(i).getId());

            EditText et = new EditText(this);
            ViewGroup layout = (ViewGroup) findViewById(R.id.water_activity);
            layout.addView(tv);
            layout.addView(et);
            editTextList.add(et);

        }

        /*SharedPreferences waterPref = getSharedPreferences("water", 0);
        rating.clear();
        rating.add(waterPref.getInt("0", 0));
        for(int x = 0; x < waterPref.getInt("0", 0); x++){
            rating.add(waterPref.getInt(Integer.toString(x+1),-1));
        }
        */

    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        EditText editText2 = (EditText) findViewById(R.id.edit_message2);
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
        if(rating.size() < Questions.size()) {
            for (int i = 0; i < editTextList.size(); i++) {
                if((editTextList.get(i).getText().toString()).matches("")){
                    Toast.makeText(this, "You did not select an option for q" + (i + 1), Toast.LENGTH_SHORT).show();
                    return;
                }
                rating.add(eval2(Integer.parseInt(editTextList.get(i).getText().toString())));
            }
        }
        else{
            for (int i = 0; i < editTextList.size(); i++) {
                if((editTextList.get(i).getText().toString()).matches("")){
                    Toast.makeText(this, "You did not select an option for q" + (i + 1), Toast.LENGTH_SHORT).show();
                    return;
                }
                rating.set(i,eval2(Integer.parseInt(editTextList.get(i).getText().toString())));
            }
        }
        intent.putExtra(EXTRA_MESSAGE, "water");
        intent.putExtra(LIST, rating);
        startActivity(intent);
    }

    public void checkStatus(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "water");
        SharedPreferences waterPref = getSharedPreferences("water", 0);
        rating.clear();
        rating.add(waterPref.getInt("0", 0));
        for(int x = 0; x < Questions.size(); x++){
            rating.add(waterPref.getInt(Integer.toString(x),-1));
        }
        startActivity(intent);
    }

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

    public int eval2(int num) {
        if(num < 3)
            return 0;
        else if (num < 6)
            return 1;
        else
            return 2;
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
}
