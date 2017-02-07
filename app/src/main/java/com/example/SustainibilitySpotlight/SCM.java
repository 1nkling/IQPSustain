package com.example.SustainibilitySpotlight;

import com.example.SustainibilitySpotlight.Struct.QuestionAndResponse;
import com.example.SustainibilitySpotlight.Struct.Response;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 1/31/17.
 */

public class SCM extends Family {

    public SCM(ArrayList<QuestionAndResponse> qrs){
        this.qrs = qrs;
        this.famID = qrs.get(0).getQuestion().getFamID();
        for (QuestionAndResponse qr: qrs){
            content.addView(qr.getContent());
        }

    }

    @Override
    ArrayList<Response> getResponses() {
        return null;
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

}
