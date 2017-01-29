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
public class WaterTipsActivity extends AppCompatActivity {
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
        name = (TextView) findViewById(R.id.Name);
        name.setText("Water Tips");
        content = (LinearLayout) findViewById(R.id.content);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainTips();
            }
        });
        TextView tip1 = new TextView(this);
        tip1.setText("Reducing the flow of your appliances is critical, Low flow everything!!");
        TextView tip2 = new TextView(this);
        tip2.setText("Watering your yard at night saves lots of water otherwise lsot to evaporation");
        tip1.setTextSize(20);
        tip2.setTextSize(2);
        content.addView(tip1);
        content.addView(tip2);
    }

    private void openMainTips() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }
}
