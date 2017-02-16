package com.example.SustainibilityStoplight;

import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.Struct.Response;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class SCM extends iFamily {

    public SCM(ArrayList<QuestionAndResponse> qrs){
        super(qrs);
    }

    @Override
    void init() {
        for (QuestionAndResponse qr : qrs){
            this.addView(qr);
        }
    }

    @Override
    ArrayList<Response> getResponses(){
        ArrayList<Response> r = new ArrayList<>();
        for (QuestionAndResponse qr : qrs){
            r.add(qr.getResp());
        }
        return r;
    }

    @Override
    int getScore() {
        int scalar = 1;
        int rate = 0;
        int frequency = 0;

        for (QuestionAndResponse qr: qrs){
            if (qr.getQuestion().getMyFamType() == 0){
                scalar = scalar * qr.getResp().getResp();
            }
            if (qr.getQuestion().getMyFamType() == 1){
                rate += qr.getResp().getResp();
            }
            if (qr.getQuestion().getMyFamType() == 2){
                frequency += qr.getResp().getResp();
            }
        }
        return scalar*rate*frequency;
    }

    @Override
    void update(iFamily fam) {
        GenericFamily me = (GenericFamily) fam;
        for (int i= 0; i < me.getChildCount(); i++){
            qrs.set(i, (QuestionAndResponse) me.getChildAt(i));
        }
    }

}
