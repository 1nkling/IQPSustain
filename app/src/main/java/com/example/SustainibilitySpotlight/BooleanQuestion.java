package com.example.SustainibilitySpotlight;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.SustainibilitySpotlight.Struct.Question;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class BooleanQuestion extends RadioGroup implements IQuestion {

    Question q;
    RadioButton yes;
    RadioButton no;

    public BooleanQuestion(Question q, int resp, Context context) {
        super(context);
        this.q = q;
        this.setOrientation(LinearLayout.HORIZONTAL);
        yes = new RadioButton(context);
        no = new RadioButton(context);
        yes.setText("Yes");
        no.setText("No");
        setAnswer(resp);
        no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswer(0);
            }
        });
        yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswer(1);
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        this.addView(yes);
        this.addView(no);
    }

    //TODO maybe do more error checking
    public int getAnswer(){
        if (yes.isChecked()){
            return 1;
        }
        return 0;
    }

    @Override
    public void setAnswer(int answer) {
        if (answer == 1) {
            yes.toggle();
            no.setChecked(false);
        } else {
            no.toggle();
            yes.setChecked(false);
        }
    }
}
