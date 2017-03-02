package com.example.SustainibilityStoplight;

import android.widget.LinearLayout;

import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.Struct.Response;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 1/31/17.
 */

public abstract class iFamily extends LinearLayout {

    ArrayList<QuestionAndResponse> qrs;

    int famID;

    String dim;

    // Should never be called
    public iFamily(ArrayList<QuestionAndResponse> src) {
        super(src.get(0).getContext());
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        this.qrs = src;
        this.dim = qrs.get(0).getQuestion().getDimension();
        this.famID = qrs.get(0).getQuestion().getFamID();
        init();
    }

    abstract void init();

    abstract ArrayList<Response> getResponses();

    abstract int getScore();

    abstract void update(iFamily fam);

    public void setResponses(ArrayList<Response> r){
        int i = 0;
        for (Response resp: r){
            for (QuestionAndResponse qr : qrs){
                if (resp.getId() == qr.getId()){
                    qr.setResp(resp);
                    break;
                }
            }
        }
    }

    public String toString(){
        String resp = " ";
        for (QuestionAndResponse qr : qrs){
            resp = resp + qr.toString();
            resp = resp + " ";
        }
        return resp;
    }
}
