package com.example.cypuzzle2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AddFriendActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    ImageButton button;
    EditText username;
    private int UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);
        button = findViewById(R.id.add_friend_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });
        mQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        UserID = intent.getIntExtra("UserID", -1);

        username = (EditText) findViewById(R.id.inputUsername);
        final Button addFriendButton = (Button) findViewById(R.id.addFriendButton);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                addFriendButton.setEnabled(!username.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley();
            }
        });
        }
    public void openMain(){
        //Intent intent = new Intent(this, FriendsActivity.class);
        //intent.putExtra("UserID", UserID);
        //startActivity(intent);
        onBackPressed();
    }
    public void volley() {
        final String content = username.getText().toString();

        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/users/username:" + content;
        Log.d("userName", content);

        Log.d("String url", url);

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject test = response.getJSONObject(0);
                    Integer friendID = test.getInt("userID");
                    Log.d("FriendId", String.valueOf(friendID));
                    addFriend(friendID);
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
    public void addFriend(int friendID) {

        final int friendID2 = friendID;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int userID = UserID;
                    String urlString = "http://coms-309-bs-6.misc.iastate.edu:8080/addFriendship";
                    URL url = new URL(urlString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    con.setRequestProperty("Accept","application/json");
                    con.setDoOutput(true);
                    con.setDoInput(true);

                    JSONObject params = new JSONObject();
                    params.put("userID", userID);
                    params.put("friendID", friendID2);

                    Log.i("JSON", params.toString());
                    DataOutputStream os = new DataOutputStream(con.getOutputStream());
                    os.writeBytes(params.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(con.getResponseCode()));
                    Log.i("MSG" , con.getResponseMessage());

                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


}