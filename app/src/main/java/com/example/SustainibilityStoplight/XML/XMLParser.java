package com.example.SustainibilityStoplight.XML;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.example.SustainibilityStoplight.Struct.Response;
import com.example.SustainibilityStoplight.Struct.Question;

/**
 * Created by danso on 12/10/2016.
 */

public class XMLParser {
    private ArrayList<Question> questions;
    private Question question;
    private String text;

    public void XMLPullParserHandler() {
        questions = new ArrayList<Question>();
    }

    public List<Question> getEmployees() {
        return questions;
    }

    public ArrayList<Question> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        questions = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        tagname = parser.getName();
                        if (tagname.equalsIgnoreCase("question")) {
                            // create a new instance of employee
                            question = new Question();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        tagname = parser.getName();
                        if (tagname.equalsIgnoreCase("question")) {
                            // add employee object to list
                            questions.add(question);
                        }
                         else if (tagname.equalsIgnoreCase("q")) {
                            question.setQ(text);
                        } else if (tagname.equalsIgnoreCase("dimension")) {
                            question.setDimension(text);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            question.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("respType")) {
                            question.setRespType(text);
                        } else if (tagname.equalsIgnoreCase("maxScore")) {
                            question.setMaxScore(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("rec")) {
                            question.setRec(text);
                        } else if (tagname.equalsIgnoreCase("famID")) {
                            question.setFamID(Integer.parseInt(text));
                        }
                        else if (tagname.equalsIgnoreCase("famType")) {
                            question.setFamType(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("myFamType")) {
                            question.setMyFamType(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("weight")) {
                        question.setWeight(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("isLowGood")) {
                            if (text.equalsIgnoreCase("true"))
                                question.setLowGood(true);
                            else
                                question.setLowGood(false);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // Does NOT check if the files exist will throw exception
    public HashMap<String, ArrayList<Response>> parse2(Context context) throws FileNotFoundException {
        HashMap<String, ArrayList<Response>> map = new HashMap<>();
        File is = context.getDir("responses", 0);
        for (File f : is.listFiles()) {
            String name = f.getName();
            ArrayList<Response> resps = new ArrayList<>();
            Scanner scanner = new Scanner(f);
            boolean bool = true;
            int tempID = -999;
            String tempResp;
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                if (bool){
                    tempID = Integer.parseInt(input);
                    bool = false;
                } else {
                    bool = true;
                    tempResp = input;
                    resps.add(new Response(tempID, name, Integer.parseInt(tempResp), context));
                }

            }
            map.put(name, resps);
        }

        return map;
    }
}
