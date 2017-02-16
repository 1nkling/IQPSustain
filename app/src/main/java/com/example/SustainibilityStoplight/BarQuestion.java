package com.example.SustainibilityStoplight;

import android.content.Context;
import android.widget.SeekBar;

import com.example.SustainibilityStoplight.Struct.Question;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class BarQuestion extends SeekBar implements IQuestion {

    Question q;

    public BarQuestion(Question q, int resp, Context context) {
        super(context);
        this.q = q;
        this.setMax(q.getMaxScore());
        // Puts the bar either in the previous place or the worst scoring
       // if (resp.getResp() > 0) {
         //   bar.setProgress(resp.getResp());
        //} else
            if (q.isLowGood()) {
            this.setProgress(q.getMaxScore());
            //resp.setResp(q.getMaxScore());
        } else {
            this.setProgress(0);
        }
        this.setAnswer(resp);
   }

    public int getAnswer(){
        return this.getProgress();
    }

    @Override
    public void setAnswer(int answer) {
        this.setProgress(answer);
    }
}
