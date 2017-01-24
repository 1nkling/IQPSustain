package com.example.SustainibilitySpotlight.XML;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.example.SustainibilitySpotlight.Response;
import com.example.SustainibilitySpotlight.Struct.Question;

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
                            Log.d("makeQ", "question is made");
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

    public HashMap<String, ArrayList<Response>> parse2(File src, Context context) throws FileNotFoundException {
        HashMap<String, ArrayList<Response>> map = new HashMap<>();

        for (File f : src.listFiles()) {
            ArrayList<Response> resps = new ArrayList<>();
            Scanner scanner = new Scanner(f);
            String input = scanner.next();
            int first = 0;
            int second = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '\n') {
                    if (first == 0) {
                        first = i;
                    } else if (second == 0) {
                        second = i;
                    }
                }
            }
            CharSequence id = input.subSequence(1, first - 1);
            CharSequence text = input.substring(second + 1, input.length() - 1);
            Response resp = new Response(Integer.parseInt(id.toString()), f.getName(), text.toString(), context);
            resps.add(resp);
            map.put(f.getName(), resps);

        }

        return map;
    }
}
