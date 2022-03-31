package com.cypuzzle.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class FriendsActivity extends AppCompatActivity {
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_main);

        button = findViewById(R.id.friends_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });

    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
