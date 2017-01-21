package com.example.helloworld.XMLParser;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.helloworld.Struct.Question;

import static com.example.helloworld.R.id.list;

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

    public HashMap<String, ArrayList<EditText>> parse2(InputStream is, Context context){
        HashMap<String, ArrayList<EditText>> map = new HashMap<>();
        XmlPullParserFactory factory;
        XmlPullParser parser;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            String currentDim = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        ArrayList<EditText> temp = new ArrayList<>();
                        tagname = parser.getName();
                        if (tagname.equalsIgnoreCase("dimension")) {
                            map.put(text, temp);
                            currentDim = text;
                        }
                        else if (currentDim != ""){
                            EditText txt = new EditText(context);
                            txt.setText(text);
                            map.get(currentDim).add(txt);
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
        return map;
    }
}
