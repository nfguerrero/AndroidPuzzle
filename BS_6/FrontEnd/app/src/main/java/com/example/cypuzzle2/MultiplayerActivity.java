package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MultiplayerActivity extends AppCompatActivity{

    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_main);

        button = findViewById(R.id.multiplayer_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openNewPuzzleMain();
            }
        });

    }

    /**
     * helper method to open the Main Menu page
     */
    public void openNewPuzzleMain(){
        Intent intent = new Intent(this, NewPuzzleActivity.class);
        startActivity(intent);
    }
}
