package com.example.SustainibilitySpotlight.XML;

import android.content.Context;
import android.support.test.espresso.core.deps.guava.base.Charsets;

import com.example.SustainibilitySpotlight.Struct.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by peterdebrine on 1/22/17.
 */
public class XMLWriter {

    // Does not actually write xml just to be clear
    // TODO decide to rename this

    public void writeResponses(ArrayList<Response> resps, Context context, String dim) throws IOException {
        File storage = context.getDir("responses", 0);
        File file = new File(storage, dim);
        file.setWritable(true);
        file.setReadable(true);
        FileOutputStream f = new FileOutputStream(file);
        for (int i = 0; i < resps.size(); i++){
            Response temp = resps.get(i);
            // TODO considering hardcoding this into xml
            String id = temp.getId() + "\n";
            String resp = temp.getResp() + "\n";
            byte[] bytes1 = id.getBytes(Charsets.UTF_8);
            byte[] bytes2 = resp.getBytes(Charsets.UTF_8);
            f.write(bytes1);
            f.write(bytes2);
        }
        f.close();
    }
}
