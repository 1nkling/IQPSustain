package com.example.helloworld;

import android.content.Context;

import com.example.helloworld.Struct.Question;
import com.example.helloworld.XMLParser.XMLParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by peterdebrine on 12/15/16.
 */

public class SurveyMap {
    private HashMap<String, List<Question>> map;
    private List<Question> questions;


    SurveyMap(Context context) throws IOException {
        XMLParser parser = new XMLParser();
        questions = parser.parse(context.getAssets().open("questions.xml"));
        int size = questions.size();
        int i = 0;
        map = new HashMap<>();
        for (; i < size; i++){
            String name = questions.get(i).getDimension();
            if (!(map.containsKey(name))){
                map.put(name, new ArrayList<Question>());
                map.get(name).add(questions.get(i));
            }
            else map.get(name).add(questions.get(i));
        }

    }

    public List<Question> getQuestions(String name){
        return map.get(name);
    }

}
