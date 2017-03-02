package com.example.SustainibilityStoplight.Struct;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.SustainibilityStoplight.BarQuestion;
import com.example.SustainibilityStoplight.BooleanQuestion;
import com.example.SustainibilityStoplight.IQuestion;

/**
 * Created by peterdebrine on 1/22/17.
 */

public class QuestionAndResponse extends LinearLayout {

    Question question;
    Response resp;
    int id;
    TextView q;
    IQuestion iq;


    public LinearLayout getContent() {
        return this;
    }

    public TextView getQ() {
        return q;
    }

    public void setQ(TextView q) {
        this.q = q;
    }

    public QuestionAndResponse(Question question, Response resp, Context c) {
        super(c);
        this.question = question;
        this.resp = resp;
        q = new TextView(c);
        q.setText(question.getQ());
        this.setOrientation(LinearLayout.VERTICAL);
        this.addView(q);
        if (question.getRespType().equals("open")){
            BarQuestion bq = new BarQuestion(question, resp.getResp(), c);
            bq.setAnswer(resp.getResp());
            iq = bq;
            this.addView(bq);
        } else if (question.getRespType().equals("bool")){
            BooleanQuestion bq = new BooleanQuestion(question, resp.getResp(), c);
            bq.setAnswer(resp.getResp());
            iq = bq;
            this.addView(bq);
        }
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 20, 10, 10);
        this.setLayoutParams(params);
        if (resp.getId() == question.getId()){
            this. id = resp.getId();
        }
        else this.id = -999;

    }

    public IQuestion getIq(){
        if (iq == null){
            throw new RuntimeException("IQuestion not set");
        }
        return this.iq;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Response getResp() {
        resp.setResp(iq.getAnswer());
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
        return " question: " + this.question.toString() + " resp: " + this.resp.toString();
    }

    public boolean needsRec(){
        //TODO
        if (resp.getResp() < question.getMaxScore()){
            return true;
        }
        return false;
    }

    public int getScore(){
        int w = question.getWeight();
        int score = w * iq.getAnswer();
        return score;
    }

    public int getMax() {
        int max;
        max = question.getWeight() * question.getMaxScore();
        return max;
    }
}
