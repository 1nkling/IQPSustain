package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclingActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "";
    int i = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_recycling);View back = findViewById(R.id.main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
    }

    private void openMainNew() {
        Intent intent = new Intent(this, Main_NewActivity.class);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        if (i == -1) {
            Toast.makeText(this, "You did not select an option for q1", Toast.LENGTH_SHORT).show();
            return;
        }
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, conv(i));
            startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.r_yes:
                if (checked)
                    i = 0;
                break;
            case R.id.r_unsure:
                if (checked)
                    i = 1;
                break;
            case R.id.r_no:
                if (checked)
                    i = 2;
                break;
            default:
                i = -1;
                break;

        }
    }

    public String eval(int num) {
        if(num < 3)
            return "good! \n";
        else if (num < 6)
            return "alright... \n";
        else
            return "bad :( \n";
    }

    public String conv(int num) {
        if(num == 0)
            return "good! \n";
        else if (num == 1)
            return "alright... \n";
        else
            return "bad :( \n";
    }

}
