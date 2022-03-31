package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;



/**
 * MainActivity class
 * Contains all activity associated with the Main Menu page
 * contains all links to the other app pages
 */
public class MainActivity extends AppCompatActivity implements OnClickListener{

    private String UserName;
    private int UserID;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        UserName = intent.getStringExtra("UserName");
        UserID = intent.getIntExtra("UserID", -1);
        email = intent.getStringExtra("Email");

//        Log.d("UserName", UserName);
//        Log.d("UserID", String.valueOf(UserID));
//        Log.d("Email", email);



        //ImageButton newpuzzle_button = (ImageButton) findViewById(R.id.newpuzzle_button);
        ImageButton puzzledifficulty_button = (ImageButton) findViewById(R.id.newpuzzle_button);
        ImageButton createpuzzle_button = (ImageButton) findViewById(R.id.createpuzzle_button);
        ImageButton leaderboard_button = (ImageButton) findViewById(R.id.leaderboard_button);
        ImageButton achievements_button = (ImageButton) findViewById(R.id.achievements_button);
        ImageButton friends_button = (ImageButton) findViewById(R.id.friends_button);
        ImageButton settings_button = (ImageButton) findViewById(R.id.settings_button);


        //newpuzzle_button.setOnClickListener(this);
        puzzledifficulty_button.setOnClickListener(this);
        createpuzzle_button.setOnClickListener(this);
        leaderboard_button.setOnClickListener(this);
        achievements_button.setOnClickListener(this);
        friends_button.setOnClickListener(this);
        settings_button.setOnClickListener(this);



    }

//    /**
//     * helper method to open the NewPuzzle page
//     */
//    public void openNewPuzzle(){
//        Intent intent = new Intent(this, NewPuzzleActivity.class);
//        startActivity(intent);
//    }

        /**
     * helper method to open the NewPuzzle page
     */
    public void openPuzzleDifficulty(){
        Intent intent = new Intent(this, PuzzleDifficulty.class);
        startActivity(intent);
    }


    /**
     * helper method to open the CreatePuzzle page
     */
    public void openCreatePuzzle(){
        Intent intent = new Intent(this, CreatePuzzleActivity.class);
        startActivity(intent);
    }

    /**
     * helper method to open the Leaderboard page
     */
    public void openLeaderboard(){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    /**
     * helper method to open the Achievements page
     */
    public void openAchievements(){
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }

    /**
     * helper method to open the Friends page
     */
    public void openFriends(){
        Intent intent = new Intent(this, FriendsActivity.class);
        intent.putExtra("UserID", UserID);
        startActivity(intent);
    }

    /**
     * helper method to open the Settings page
     */
    public void openSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newpuzzle_button:
                //openNewPuzzle();
                openPuzzleDifficulty();
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
