package com.example.dsherratt.trialapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TextView convertedValue; //these are my vars
    EditText degF; ////
    EditText degC;  ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        convertedValue = (TextView) findViewById(R.id.convertedValue); ///////    this is the start of my code    /////////////////////////
        degF = (EditText) findViewById(R.id.tempF);
        degC = (EditText) findViewById(R.id.tempC);

            Button calcF2C = (Button) findViewById(R.id.calcF2C);
            calcF2C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    float degreesF = Float.parseFloat(degF.getText().toString());
                    float convC = (((degreesF-32) * 5)/9);
                    convertedValue.setText(Float.toString(convC));
                }
        });

        Button calcC2F = (Button) findViewById(R.id.calcC2F);
        calcC2F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float degreesC = Float.parseFloat(degC.getText().toString());
                float convF = (((degreesC*9)/5)+32);
                convertedValue.setText(Float.toString(convF));
            }
        });                 ////////////    this is the end of my code    //////////////////////////
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
