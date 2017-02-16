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
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
}
