package com.example.SustainibilitySpotlight;

import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclingActivity extends AbstractSurvey {

    public final static String EXTRA_MESSAGE = "";
    ArrayList<Integer> rating = new ArrayList<Integer>();
    int i = -1;
/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_recycling);
        View back = findViewById(R.id.main);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainNew();
            }
        });
    }

    @Override
    protected void init() {

    }

    private void openMainNew() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/

    public void sendMessage(View view) {
        if (i == -1) {
            Toast.makeText(this, "You did not select an option for q1", Toast.LENGTH_SHORT).show();
            return;
        }
            Intent intent = new Intent(this, DisplayMessageActivity.class);

            intent.putExtra(EXTRA_MESSAGE, "recyc");
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