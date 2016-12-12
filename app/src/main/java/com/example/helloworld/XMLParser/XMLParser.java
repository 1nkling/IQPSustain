package com.example.helloworld.XMLParser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<Question> parse(InputStream is) {
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
                        } else if (tagname.equalsIgnoreCase("id")) {
                            question.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("respType")) {
                            question.setRespType(text);
                        } else if (tagname.equalsIgnoreCase("respNum")) {
                            question.setRespNum(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("rec")) {
                            question.setRec(text);
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
}
