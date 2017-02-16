package com.example.SustainibilityStoplight;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.SustainibilityStoplight.Struct.Question;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class BooleanQuestion extends RadioGroup implements IQuestion {

    Question q;
    RadioButton yes;
    RadioButton no;
    Context c;

    public BooleanQuestion(Question q, int resp, Context context) {
        super(context);
        c = context;
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
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.addView(yes);
        this.addView(no);
    }

    //TODO maybe do more error checking
    public int getAnswer(){
        if (yes.isChecked()){
            return 1;
        } else if (no.isChecked()){
            return 0;
        }
        return -1;
    }

    @Override
    public void setAnswer(int answer) {
        if (answer == 1) {
            yes.toggle();
        } else if (answer == 0) {
            no.toggle();
        } else {
            yes.setChecked(false);
            no.setChecked(false);
        }
    }

    public Question getQuestion(){
        return q;
    }

}
