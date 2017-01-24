package com.example.SustainibilitySpotlight;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by peterdebrine on 1/22/17.
 */

public class Response {
    EditText eText;
    String text;
    int id;
    String dim;

    public Response(int id, String dim, String text, Context c) {
        this.id = id;
        this.dim = dim;
        this.text = text;
        this.eText = new EditText(c);
        eText.setText(text);
    }

    public Response(int id, EditText eText, String dim) {
        this.id = id;
        this.eText = eText;
        this.dim = dim;
        this.text = eText.getText().toString();
    }

    public void updateText(String text){
        setText(text);
        this.eText.setText(text);
    }

    public EditText geteText() {
        return eText;
    }

    public void seteText(EditText eText) {
        this.eText = eText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
