package com.example.SustainibilitySpotlight;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.example.SustainibilitySpotlight.Struct.Question;
import com.example.SustainibilitySpotlight.XML.XMLParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by peterdebrine on 12/15/16.
 */

public class SurveyMap {
    private HashMap<String, ArrayList<Question>> questionsMap;
    private HashMap<String, ArrayList<Response>> responsesMap;
    private ArrayList<Question> questions;
    ArrayList<String> dims;


    SurveyMap(Context context) throws IOException {
        XMLParser parser = new XMLParser();
        questions = parser.parse(context.getAssets().open("questions.xml"));
        int size = questions.size();
        int i = 0;
        questionsMap = new HashMap<>();
        SharedPreferences sustPref = context.getSharedPreferences("SustSpotlight", 0);
        if (sustPref.getBoolean("saved", false)) {
            responsesMap = parser.parse2(context);
        }
        for (; i < size; i++){
            String name = questions.get(i).getDimension();
            if (!(questionsMap.containsKey(name))){
                questionsMap.put(name, new ArrayList<Question>());
                questionsMap.get(name).add(questions.get(i));
            }
            else questionsMap.get(name).add(questions.get(i));
        }
        dims = new ArrayList<String>();
        for (i = 0; i < questions.size(); i++){
            if (!(dims.contains(questions.get(i).getDimension()))) {
                dims.add(questions.get(i).getDimension());
            }
        }

    }

    public ArrayList<Question> getQuestions(String name) throws RuntimeException {
        ArrayList<Question> answer = questionsMap.get(name);
        if (answer != null) {
            return answer;
        }
        else throw new RuntimeException("no questions for the dim:" + name);
    }


    public ArrayList<String> getDims(){
        return dims;
    }

    public ArrayList<Response> getResponses(String dim){
        if (responsesMap == null) throw new RuntimeException("Had not saved yet");
        ArrayList resps = responsesMap.get(dim);
        if (resps != null) {
            return resps;
        }
        // TODO probably want to have this return a list of responses with no text
        // and then we can remove some code in abstract survey probably
        else throw new RuntimeException("no responses for the dim: " + dim);
    }

    public boolean hasAnswers(){
        return !(responsesMap.isEmpty());
    }

    public boolean dimHasAnswers(String dim){
        return !(responsesMap.get(dim).isEmpty());
    }

}
