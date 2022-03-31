package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * FriendsActivity class
 * Contains all activity associated with the friends page
 */
public class HardLeaderboardActivity extends AppCompatActivity {
    private ImageButton button;
    private RequestQueue mQueue;
    ListView plistview;
    ListView tlistview;
    ListView nlistview;
    ArrayList<String> puzzleList;
    ArrayAdapter puzzleAdapter;
    ArrayList<String> timeList;
    ArrayAdapter timeAdapter;
    ArrayList<String> nameList;
    ArrayAdapter nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hard_leaderboard_main);
        mQueue = Volley.newRequestQueue(this);
        plistview = findViewById(R.id.hard_lv_puzzle);
        tlistview = findViewById(R.id.hard_lv_time);
        nlistview = findViewById(R.id.hard_lv_name);
        puzzleList = new ArrayList<>();
        timeList = new ArrayList<>();
        nameList = new ArrayList<>();
        puzzleAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, puzzleList);
        timeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, timeList);
        nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        plistview.setAdapter(puzzleAdapter);
        tlistview.setAdapter(timeAdapter);
        nlistview.setAdapter(nameAdapter);

        getHard();

        button = findViewById(R.id.back_button_from_hard_leaderboard);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLeaderboard();
            }
        });
    }

    /**
     * helper method to open the Main Menu page
     */
    public void openLeaderboard(){
        //Intent intent = new Intent(this, LeaderboardActivity.class);
        //startActivity(intent);
        onBackPressed();
    }

    /**
     * This will load the leaderboard inputs for hard puzzles
     */
    private void getHard() {
        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/results/difficulty:1";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject result = response.getJSONObject(i);
                        String puzzle = result.getString("puzzleName");
                        String user = result.getString("userName");
                        String time = result.getString("duration");
                        puzzleAdapter.add("Puzzle: "+puzzle);
                        timeAdapter.add("Time: "+time);
                        nameAdapter.add("User: "+user);

                        Log.d("Error", "Puzzle: "+puzzle+", Time: "+time+"User: "+user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
