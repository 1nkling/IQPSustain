package com.example.SustainibilitySpotlight.Struct;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by peterdebrine on 1/22/17.
 */

public class QuestionAndResponse {

    Question question;
    Response resp;
    int id;
    LinearLayout content;
    TextView q;


    public LinearLayout getContent() {
        return content;
    }

    public void setContent(LinearLayout content) {
        this.content = content;
    }

    public TextView getQ() {
        return q;
    }

    public void setQ(TextView q) {
        this.q = q;
    }

    public QuestionAndResponse(Question question, Response resp, Context c) {
        this.question = question;
        this.resp = resp;
        SeekBar bar = new SeekBar(c);
        bar.setMax(question.getMaxScore());
        // Puts the bar either in the previous place or the worst scoring
        if (resp.getResp() > 0){
            bar.setProgress(resp.getResp());
        }
        else if (question.isLowGood()){
            bar.setProgress(question.getMaxScore());
            resp.setResp(question.getMaxScore());
        }
        else {
            bar.setProgress(0);
        }

        q = new TextView(c);
        q.setText(question.getQ());
        content = new LinearLayout(c);
        content.setOrientation(LinearLayout.VERTICAL);
        content.addView(q);
        content.addView(bar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 20, 10, 10);
        content.setLayoutParams(params);
        if (resp.getId() == question.getId()){
            this. id = resp.getId();
        }
        else this.id = -999;

    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "q: " + this.q.toString() + " resp: " + this.resp.toString();
    }

    public boolean needsRec(){
        //TODO
        if (resp.getResp() < question.getMaxScore()){
            return true;
        }
        return false;
    }


}
