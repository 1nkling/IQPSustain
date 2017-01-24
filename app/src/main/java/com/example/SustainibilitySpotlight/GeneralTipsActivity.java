package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by peterdebrine on 12/13/16.
 */
public class GeneralTipsActivity extends AppCompatActivity {
    LinearLayout content;
    View back;
    TextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_display);
        init();
    }

    private void init() {
        name = (TextView) findViewById(R.id.name);
        name.setText("General Tips");
        content = (LinearLayout) findViewById(R.id.content);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainTips();
            }
        });
        TextView tip1 = new TextView(this);
        tip1.setTextSize(20);
        tip1.setText("At every moment people often have things on that do not need to be," +
                "you should make a conscious effort to minimize this kind of waste. For example leaving the refrigerator open.");
        content.addView(tip1);
    }

    private void openMainTips() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }
}
