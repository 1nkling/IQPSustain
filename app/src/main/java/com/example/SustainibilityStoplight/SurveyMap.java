package com.example.SustainibilityStoplight;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.SustainibilityStoplight.Struct.Question;
import com.example.SustainibilityStoplight.Struct.Response;
import com.example.SustainibilityStoplight.Struct.QuestionAndResponse;
import com.example.SustainibilityStoplight.XML.XMLParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by peterdebrine on 12/15/16.
 */

public class SurveyMap {
    private HashMap<String, ArrayList<Question>> questionsMap;
    private HashMap<String, ArrayList<Response>> responsesMap;
    private HashMap<String, ArrayList<QuestionAndResponse>> QandRMap;
    private ArrayList<Question> questions;
    ArrayList<String> dims;
    Context c;


    SurveyMap(Context context) throws IOException {
        c = context;
        XMLParser parser = new XMLParser();
        questions = parser.parse(context.getAssets().open("questions.xml"));
        int size = questions.size();
        int i = 0;
        questionsMap = new HashMap<>();
        responsesMap = new HashMap<>();
        QandRMap = new HashMap<>();
        SharedPreferences sustPref = context.getSharedPreferences("SustStoplight", 0);

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

        if (sustPref.getBoolean("saved", false)) {
            responsesMap = parser.parse2(context);
        }

        for (String dim : dims){
            if (responsesMap.get(dim) == null){
                ArrayList<Response> resps = new ArrayList<>();
                for (Question q: questionsMap.get(dim)){
                    resps.add(new Response(q, context));
                }
                responsesMap.put(dim, resps);
            }
        }

        for (String dim: dims){
            ArrayList<QuestionAndResponse> qrs = new ArrayList<>();
            for (i = 0; i< questionsMap.get(dim).size(); i++){
                ArrayList<Question> quest = questionsMap.get(dim);
                ArrayList<Response> r = responsesMap.get(dim);
                if(i == r.size()){
                    r.add(new Response(quest.get(i), context));
                }
                responsesMap.remove(dim);
                responsesMap.put(dim, r);
                qrs.add(new QuestionAndResponse(quest.get(i),
                        r.get(i), context));
            }
            QandRMap.put(dim, qrs);
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
        ArrayList resps = responsesMap.get(dim);
        if (resps != null) {
            return resps;
        }
        // Should NEVER happen
        else throw new RuntimeException("no responses for the dim: " + dim);
    }

    public HashMap<String, ArrayList<QuestionAndResponse>> getQRMap(){
        return QandRMap;
    }

    public ArrayList<QuestionAndResponse> getQRs(String dim){
        return QandRMap.get(dim);
    }

    public boolean hasAnswers(){
        return !(responsesMap.isEmpty());
    }

    public boolean dimHasAnswers(String dim){
        return !(responsesMap.get(dim).isEmpty());
    }

    public String getNextDim(String name) throws IOException {
        // Minus one so that you will no end up out of bounds
        for (int i = 0; i < dims.size()-  1; i++){
            if (dims.get(i).equals(name)){
                return dims.get(i + 1);
            }
        }
        throw new IOException();
    }

    public String getPrevDim(String name) throws IOException {
        // I starts at one so that you cannot start out of bounds
        for (int i = 1; i < dims.size(); i++){
            if (dims.get(i).equals(name)){
                return dims.get(i - 1);
            }
        }
        throw new IOException();
    }
}
