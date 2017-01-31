package com.example.SustainibilitySpotlight.Struct;

import android.content.Context;
import android.widget.SeekBar;

/**
 * Created by peterdebrine on 1/22/17.
 */

public class Response {

    int resp;
    int id;
    String dim;

    public Response(int id, String dim, int resp, Context c) {
        this.id = id;
        this.dim = dim;
        this.resp = resp;
    }

    public Response(Question q, Context c) {
        this.id = q.getId();
        this.dim = q.getDimension();
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
