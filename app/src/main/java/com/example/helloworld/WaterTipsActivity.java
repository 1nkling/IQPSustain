package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by peterdebrine on 12/13/16.
 */
public class WaterTipsActivity extends AppCompatActivity {
    LinearLayout content;
    View back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_display);
        init();
    }

    private void init() {
        content = (LinearLayout) findViewById(R.id.content);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainTips();
            }
        });
    }

    private void openMainTips() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }
}
