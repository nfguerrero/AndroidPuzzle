package com.example.cypuzzle2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class FriendsActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private ImageButton addFriend;
    private ImageButton button;
    private ImageButton chat;
    ListView listview;
    EditText username;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    private int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_main);
        mQueue = Volley.newRequestQueue(this);

        Log.d("UserID", String.valueOf(UserID));
        if (UserID == 0) {
            Intent intent = getIntent();
            UserID = intent.getIntExtra("UserID", -1);
        }



        addFriend = findViewById(R.id.friends_addfriend_button);
        addFriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonClick(addFriend);
            }
        });

        chat = findViewById(R.id.chat_button);
        chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openChat();
            }
        });

        listview = (ListView) findViewById(R.id.LVFriend);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);

        button = findViewById(R.id.friends_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });

        /**
         * THIS IS NOT AN ADEQUATE SOLUTION
         *
         * Doing this calls a separate volley call for each friend which is very laggy(and out of order because it is technically a listener)
         * This is a proof of concept to help show how a volley call can be used
         * Will want to have one volley call that gets a list of friends which can then be parsed out(will need to update the database for this, if not already done)
         */
        getFriendIds();
    }






    public void onButtonClick(View view) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        intent.putExtra("UserID", UserID);
        startActivity(intent);
    }

    public void openMain() {
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        onBackPressed();
    }

    public void openChat(){
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void volley(ArrayList<Integer> friendArray) {
        for (int i = 0; i < friendArray.size(); i++) {
            final int id = friendArray.get(i);

            String url = "http://coms-309-bs-6.misc.iastate.edu:8080/users/user:" + id;

            JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject test = response.getJSONObject(0);
                        String name = test.getString("userName");
                        Log.d("Current Name", name);
                        arrayAdapter.add("This friend was found with Volley ID:" + id + ": " + name);
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

    public void getFriendIds() {
        //Hard coded id until login is created
        final ArrayList<Integer> friendArray = new ArrayList<Integer>();

        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/friendship:" + UserID;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject test = response.getJSONObject(i);
                        Integer currentFriendID = test.getInt("friendID");
                        Log.d("Current FriendId", currentFriendID.toString());
                        friendArray.add(currentFriendID);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volley(friendArray);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonArrayRequest);
    }
}
