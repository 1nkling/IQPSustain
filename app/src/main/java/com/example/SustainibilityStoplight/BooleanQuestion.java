package com.example.SustainibilityStoplight;

import android.content.Context;
import android.util.Log;
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
    int answer;

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
        if (resp == 1){
            yes.toggle();
        } else if (resp == 0){
            no.toggle();
        }
        no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswer(0);
                yes.setChecked(false);
                RadioButton rb = (RadioButton) view;
                if (!(rb.isChecked())){
                    rb.toggle();
                }
            }
        });
        yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswer(1);
                no.setChecked(false);
                RadioButton rb = (RadioButton) view;
                if (!(rb.isChecked())){
                    rb.toggle();
                }
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.addView(yes);
        this.addView(no);

    }


    public int getAnswer(){
        return answer;
    }

    @Override
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Question getQuestion(){
        return q;
    }

}
