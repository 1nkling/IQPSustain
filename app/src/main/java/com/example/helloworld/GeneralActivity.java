package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by peterdebrine on 12/6/16.
 */
public class GeneralActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        View back = findViewById(R.id.main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
    }

    private void openMainNew(){
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }
}
