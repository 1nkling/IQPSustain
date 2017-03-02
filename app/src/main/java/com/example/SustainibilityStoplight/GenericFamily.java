package com.example.SustainibilityStoplight;

import com.example.SustainibilityStoplight.Struct.Question;
import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.Struct.Response;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 2/14/17.
 */

public class GenericFamily extends iFamily {

    public GenericFamily(ArrayList<QuestionAndResponse> src) {
        super(src);
    }

    @Override
    void init() {
        for (QuestionAndResponse qr : qrs){
            this.addView(qr);
        }
    }

    @Override
    ArrayList<Response> getResponses() {
        ArrayList<Response> r = new ArrayList<>();
        for (int i = 0; i < this.getChildCount(); i++) {
            QuestionAndResponse qr = (QuestionAndResponse) this.getChildAt(i);
            r.add(qr.getResp());
        }
        return r;
    }

    @Override
    int getScore() {
        int score = 0;
        for (QuestionAndResponse qr : qrs){
            score += qr.getScore();
        }
        return score;
    }

    @Override
    void update(iFamily fam) {
        GenericFamily me = (GenericFamily) fam;
        for (int i= 0; i < me.getChildCount(); i++){
            qrs.set(i, (QuestionAndResponse) me.getChildAt(i));
        }
    }
}
