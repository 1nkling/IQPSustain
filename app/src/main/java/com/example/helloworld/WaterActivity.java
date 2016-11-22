package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class WaterActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "";
    int i = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        EditText editText2 = (EditText) findViewById(R.id.edit_message2);
        if((editText.getText().toString()).matches("")){
            Toast.makeText(this, "You did not select an option for q1", Toast.LENGTH_SHORT).show();
            return;
        }
        else if((editText2.getText().toString()).matches("")){
            Toast.makeText(this, "You did not select an option for q2", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(i == -1){
            Toast.makeText(this, "You did not select an option for q3", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        int num = Integer.parseInt(editText.getText().toString());
        String message = eval(num);
        int num2 = Integer.parseInt(editText2.getText().toString());
        String message2 = eval(num2);
        String fString = message.concat(message2);
        String ffString = fString.concat(conv(i));
        intent.putExtra(EXTRA_MESSAGE, ffString);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.r_good:
                if (checked)
                    i = 0;
                break;
            case R.id.r_med:
                if (checked)
                    i = 1;
                break;
            case R.id.r_bad:
                if (checked)
                    i = 2;
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
