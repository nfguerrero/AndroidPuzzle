package com.example.quadratic_formula;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MathActivity extends AppCompatActivity {
    double A1;
    double B1;
    double C1;
    double result;
    double result2;

    EditText A;
    EditText B;
    EditText C;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        String TAG = "MathActivity";

        A = (EditText) findViewById(R.id.A);
        B = (EditText) findViewById(R.id.B);
        C = (EditText) findViewById(R.id.C);

        configureSwitchButton();
    }

        private void configureSwitchButton() {
            submitButton = (Button) findViewById(R.id.goButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    A1 = Double.valueOf(A.getText().toString());
                    B1 = Double.valueOf(B.getText().toString());
                    C1 = Double.valueOf(C.getText().toString());
                    String TAG = "MathActivity";

                    result =  ((B1*-1) + Math.sqrt((B1*B1) - (4*A1*C1))) / (2*A1);
                    result2 = ((B1*-1) - Math.sqrt((B1*B1) - (4*A1*C1))) / (2*A1);
                    Intent intent = new Intent(MathActivity.this, DisplayResult.class);
                    intent.putExtra("result", result);
                    intent.putExtra("result2", result2);
                    Log.i(TAG, " " + A1 + B1 + C1 + ((B1*-1) + Math.sqrt((B1*B1) - (4*A1*C1))) / (2*A1));
                    Log.i(TAG, " " + result2);


                    startActivity(intent);

                }
            });
        }



}
