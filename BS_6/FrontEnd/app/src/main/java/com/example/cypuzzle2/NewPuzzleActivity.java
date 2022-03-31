package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;


/**
 * NewPuzzleActivity class
 * Contains all activity associated with the NewPuzzle page
 */
public class NewPuzzleActivity extends AppCompatActivity {
    private ImageButton button, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpuzzle_main);


        button = findViewById(R.id.newpuzzle_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });

//        button2 = findViewById(R.id.multiplayer_button);
//        button2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                openMulti();
//            }
//        });

        LinearLayout app_layer11 = (LinearLayout) findViewById (R.id.group1_1);
        LinearLayout app_layer12 = (LinearLayout) findViewById (R.id.group1_2);
        LinearLayout app_layer13 = (LinearLayout) findViewById (R.id.group1_3);
        LinearLayout app_layer14 = (LinearLayout) findViewById (R.id.group1_4);
        LinearLayout app_layer15 = (LinearLayout) findViewById (R.id.group1_5);
        LinearLayout app_layer21 = (LinearLayout) findViewById (R.id.group2_1);
        LinearLayout app_layer22 = (LinearLayout) findViewById (R.id.group2_2);
        LinearLayout app_layer23 = (LinearLayout) findViewById (R.id.group2_3);
        LinearLayout app_layer31 = (LinearLayout) findViewById (R.id.group3_1);
        LinearLayout app_layer32 = (LinearLayout) findViewById (R.id.group3_2);
        LinearLayout app_layer33 = (LinearLayout) findViewById (R.id.group3_3);
        app_layer11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
        app_layer33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPuzzle();
            }
        });
    }


    /**
     * helper method to open the Main Menu page
     */
    public void openMain(){
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        onBackPressed();
    }

    /**
<<<<<<< HEAD
     * helper method to open the open puzzle page
=======
     * helper method to open the puzzle
>>>>>>> create_puzzle
     */
    public void openPuzzle(){
        Intent intent = new Intent(this, MainPuzzleActivity.class);
        startActivity(intent);
    }

    /**
     * helper method to open the multiplayer page
     */
    public void openMulti(){
        Intent intent = new Intent(this, MultiplayerActivity.class);
        startActivity(intent);
    }




}