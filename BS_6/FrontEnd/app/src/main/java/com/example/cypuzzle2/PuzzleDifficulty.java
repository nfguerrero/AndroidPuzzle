package com.example.cypuzzle2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;



public class PuzzleDifficulty extends AppCompatActivity {
    private ImageButton button;
    private Switch easy_switch, medium_switch, hard_switch;
    private static int diff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_main);

        easy_switch = findViewById(R.id.easy_switch);
        medium_switch = findViewById(R.id.medium_switch);
        hard_switch = findViewById(R.id.hard_switch);

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE);
        easy_switch.setChecked(sharedPrefs.getBoolean("easy_switch", true));
        medium_switch.setChecked(sharedPrefs.getBoolean("medium_switch", false));
        hard_switch.setChecked(sharedPrefs.getBoolean("hard_switch", false));
        diff = 1;


//        if( !easy_switch.isChecked() && !medium_switch.isChecked() && !hard_switch.isChecked() ){
//            diff=1;
//            easy_switch.setChecked(true);
//
//        }


        easy_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    medium_switch.setChecked(false);
                    hard_switch.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                    editor.putBoolean("easy_switch", true);
                    editor.putBoolean("medium_switch", false);
                    editor.putBoolean("hard_switch", false);
                    editor.commit();
//                    medium_switch.setChecked(false);
//                    hard_switch.setChecked(false);
                      diff = 1;
                }else{
                    if( !medium_switch.isChecked() && !hard_switch.isChecked() ){
                        easy_switch.setChecked(true);
                        SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                        editor.putBoolean("easy_switch", true);
                        editor.putBoolean("medium_switch", false);
                        editor.putBoolean("hard_switch", false);
                        editor.commit();
                        diff=1;
                    }
                }
            }
        });


        medium_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    easy_switch.setChecked(false);
                    hard_switch.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                    editor.putBoolean("easy_switch", false);
                    editor.putBoolean("medium_switch", true);
                    editor.putBoolean("hard_switch", false);
                    editor.commit();
                    diff = 2;
                }else{
                    if( !easy_switch.isChecked() && !medium_switch.isChecked() && !hard_switch.isChecked() ){
                        easy_switch.setChecked(true);
                        SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                        editor.putBoolean("easy_switch", true);
                        editor.putBoolean("medium_switch", false);
                        editor.putBoolean("hard_switch", false);
                        editor.commit();
                        diff= 1;
                    }
                }
            }
        });

        hard_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    easy_switch.setChecked(false);
                    medium_switch.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                    editor.putBoolean("easy_switch", false);
                    editor.putBoolean("medium_switch", false);
                    editor.putBoolean("hard_switch", true);
                    editor.putInt("diff", 3);
                    editor.commit();
                    diff = 3;
                }else{
                    if( !easy_switch.isChecked() && !medium_switch.isChecked() && !hard_switch.isChecked() ){
                        easy_switch.setChecked(true);
                        SharedPreferences.Editor editor = getSharedPreferences("com.example.cypuzzle2", MODE_PRIVATE).edit();
                        editor.putBoolean("easy_switch", true);
                        editor.putBoolean("medium_switch", false);
                        editor.putBoolean("hard_switch", false);
                        editor.putInt("diff", 1);
                        editor.commit();
                        diff= 1;
                    }
                }
            }
        });



        button = findViewById(R.id.difficulty_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {openMain();}});


    }




    /**
     * helper method to open the Main Menu page
     */
    public void openMain(){
        onBackPressed();
    }


    /**
     * helper method to return toggle switch position
     */
    public static int getDiff(){
        return diff;
    }






}
