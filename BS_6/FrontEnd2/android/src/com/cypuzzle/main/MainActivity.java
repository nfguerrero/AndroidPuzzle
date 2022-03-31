package com.cypuzzle.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton newpuzzle_button = (ImageButton) findViewById(R.id.newpuzzle_button);
        ImageButton createpuzzle_button = (ImageButton) findViewById(R.id.createpuzzle_button);
        ImageButton leaderboard_button = (ImageButton) findViewById(R.id.leaderboard_button);
        ImageButton achievements_button = (ImageButton) findViewById(R.id.achievements_button);
        ImageButton friends_button = (ImageButton) findViewById(R.id.friends_button);
        ImageButton settings_button = (ImageButton) findViewById(R.id.settings_button);

        newpuzzle_button.setOnClickListener(this);
        createpuzzle_button.setOnClickListener(this);
        leaderboard_button.setOnClickListener(this);
        achievements_button.setOnClickListener(this);
        friends_button.setOnClickListener(this);
        settings_button.setOnClickListener(this);

    }


    public void openNewPuzzle(){
        Intent intent = new Intent(this, NewPuzzleActivity.class);
        startActivity(intent);
    }

    public void openCreatePuzzle(){
        Intent intent = new Intent(this, CreatePuzzleActivity.class);
        startActivity(intent);
    }

    public void openLeaderboard(){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    public void openAchievements(){
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }

    public void openFriends(){
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }

    public void openSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newpuzzle_button:
                openNewPuzzle();
                break;
            case R.id.createpuzzle_button:
                openCreatePuzzle();
                break;
            case R.id.leaderboard_button:
                openLeaderboard();
                break;
            case R.id.achievements_button:
                openAchievements();
                break;
            case R.id.friends_button:
                openFriends();
                break;
            case R.id.settings_button:
                openSettings();
                break;
        }//end switch
    }//end onClick
}
