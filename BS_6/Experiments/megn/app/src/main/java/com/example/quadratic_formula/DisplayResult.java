package com.example.quadratic_formula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayResult extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        String TAG = "DisplayResult";
        double result = getIntent().getDoubleExtra("result", 0);
        String resultR = " " + result;
        double result2 = getIntent().getDoubleExtra("result2", 0);
        String result2R = " " + result2;
        TextView textView = (TextView) findViewById(R.id.printResult1);
        textView.setText(resultR);
        TextView printResult2 = (TextView) findViewById(R.id.printResult2);
        printResult2.setText(result2R);
    }
}
