package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by peterdebrine on 12/6/16.
 */

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Button back = new Button(this);
        back.setText("Back");
        ViewGroup vert = (ViewGroup) findViewById(R.id.vert);
        vert.addView(back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewMain();
            }
        });
    }

    void openNewMain(){
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }
}
