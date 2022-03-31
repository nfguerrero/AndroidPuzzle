package com.example.cypuzzle2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * LeaderboardActivity class contains bottom app bar configuration for
 * leaderboard fragments- easy, medium, and difficult
 */
public class LeaderboardActivity extends AppCompatActivity{
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_main);

        button = findViewById(R.id.leaderboard_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });

        button=findViewById(R.id.easy_leaderboard_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLeaderboardEasy();
            }
        });

        button=findViewById(R.id.medium_leaderboard_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLeaderboardMedium();
            }
        });

        button=findViewById(R.id.hard_leaderboard_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLeaderboardHard();
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

    public void openLeaderboardEasy(){
        Intent intent = new Intent(this, EasyLeaderboardActivity.class);
        startActivity(intent);
    }

    public void openLeaderboardMedium(){
        Intent intent = new Intent(this, MediumLeaderboardActivity.class);
        startActivity(intent);
    }

    public void openLeaderboardHard(){
        Intent intent = new Intent(this, HardLeaderboardActivity.class);
        startActivity(intent);
    }


}
