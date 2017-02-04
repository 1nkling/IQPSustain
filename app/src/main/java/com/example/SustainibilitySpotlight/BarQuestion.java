package com.example.SustainibilitySpotlight;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.SustainibilitySpotlight.Struct.Question;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class BarQuestion extends LinearLayout implements IQuestion {

    Question q;
    SeekBar bar;

    public BarQuestion(Question q, int resp, Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        this.q = q;
        bar = new SeekBar(context);
        bar.setMax(q.getMaxScore());
        // Puts the bar either in the previous place or the worst scoring
       // if (resp.getResp() > 0) {
         //   bar.setProgress(resp.getResp());
        //} else
            if (q.isLowGood()) {
            bar.setProgress(q.getMaxScore());
            //resp.setResp(q.getMaxScore());
        } else {
            bar.setProgress(0);
        }
        this.setAnswer(resp);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.addView(bar);
    }

    public int getAnswer(){
        return bar.getProgress();
    }

    @Override
    public void setAnswer(int answer) {
        bar.setProgress(answer);
    }
}
