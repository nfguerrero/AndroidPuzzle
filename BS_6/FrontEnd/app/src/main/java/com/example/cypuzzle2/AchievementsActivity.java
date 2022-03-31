package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;



/**
 * AchievementsActivity class
 * Contains all activity associated with the achievements page
 */
public class AchievementsActivity extends AppCompatActivity {


    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievements_main);

        button = findViewById(R.id.achievements_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });

    }


    /**
     * helper method to open the Main Menu page
     */
    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}