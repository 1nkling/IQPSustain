package com.example.SustainibilitySpotlight;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.SustainibilitySpotlight.Response;
import com.example.SustainibilitySpotlight.Struct.Question;

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

    public QuestionAndResponse(Question question, Response resp) {
        this.question = question;
        this.resp = resp;
        q = new TextView(resp.geteText().getContext());
        q.setText(question.getQ());
        content = new LinearLayout(q.getContext());
        content.setOrientation(LinearLayout.VERTICAL);
        content.addView(q);
        content.addView(resp.geteText());
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


}