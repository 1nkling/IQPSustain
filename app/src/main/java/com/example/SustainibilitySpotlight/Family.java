package com.example.SustainibilitySpotlight;

import android.widget.LinearLayout;

import com.example.SustainibilitySpotlight.Struct.QuestionAndResponse;
import com.example.SustainibilitySpotlight.Struct.Response;

import java.util.ArrayList;

/**
 * Created by peterdebrine on 1/31/17.
 */

public abstract class Family {

    ArrayList<QuestionAndResponse> qrs;

    LinearLayout content;

    int famID;

    abstract ArrayList<Response> getResponses();

    abstract int getScore();
}
