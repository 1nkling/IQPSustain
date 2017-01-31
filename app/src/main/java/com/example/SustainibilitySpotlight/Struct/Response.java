package com.example.SustainibilitySpotlight.Struct;

import android.content.Context;
import android.widget.SeekBar;

/**
 * Created by peterdebrine on 1/22/17.
 */

public class Response {
    SeekBar bar;
    int resp;
    int id;
    String dim;

    public Response(int id, String dim, int resp, Context c) {
        this.id = id;
        this.dim = dim;
        this.resp = resp;
        this.bar = new SeekBar(c);
        this.bar.setProgress(this.resp);
    }

    public Response(Question q, Context c) {
        this.id = q.getId();
        this.bar = new SeekBar(c);
        bar.setMax(q.getMaxScore());
        // TODO
        // 0 might be better; either add a min score or just check each question and see if its appropriate
        if (q.isLowGood()) {
            bar.setProgress(q.getMaxScore());
            this.resp = q.getMaxScore();
        }
        else {
            bar.setProgress(0);
            this.resp = 0;
        }
        this.dim = q.getDimension();
    }

    public void updateText(int text){
        setResp(text);
        this.bar.setProgress(text);
    }

    public SeekBar getPBar() {
        return bar;
    }

    public void setPBar(SeekBar bar) {
        this.bar = bar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }
    public String toString(){
        return dim + " id: " + id + " resp: "+ resp;
    }
}
