package com.example.onebuttonvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
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

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button button_volley_get = findViewById(R.id.button_volley_get);

        mQueue = Volley.newRequestQueue(this);

        button_volley_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    jsonParseObj();
//                    jsonGetString();
                    jsonGetArray();
//                jsonPost();
            }


        });
    }

    /**
     * This method is currently not working correctly
     */
    private void jsonParseObj(){
//        String url = "https://api.androidhive.info/volley/person_object.json";
        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/results";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                        mTextViewResult.setText(response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("users");
                    for (int i =0; i<jsonArray.length(); i++){
                        JSONObject user = jsonArray.getJSONObject(i);

                        String username = user.getString("userName");
                        int userID =user.getInt("userID");
                        if(userID == 11){
                            mTextViewResult.append(username + "/n/n");}
                    }
                }
                catch (JSONException e){
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

    /**
     * This method will return the json object of one user as a string
     */
    private void jsonGetString(){
//         String url = "https://api.androidhive.info/volley/person_object.json";
         String url = "http://coms-309-bs-6.misc.iastate.edu:8080/user/11";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                        mTextViewResult.setText(response);
                    }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);



    }

    /**
     * This will return the entire leaderboard as an array
     * will need to be parsed in order to create chart or table
     */
    private void jsonGetArray(){
      //   String url = "https://api.androidhive.info/volley/person_object.json";
        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/results/user:13";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                Log.d("Error", response.toString());
                mTextViewResult.append(response.toString());



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


    private void jsonPost(){
        //   String url = "https://api.androidhive.info/volley/person_object.json";
        String url = "http://coms-309-bs-6.misc.iastate.edu:8080/addResult";

        JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                Log.d("Error", response.toString());
//                mTextViewResult.append(response.toString());



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            //        mQueue.add(request);
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("active", "1");
                params.put("userName", "Turbopulsa");
                params.put("active", "1");
                params.put("puzzleID", "9");
                params.put("userID", "12");
                params.put("dateStarted", "2019-09-21");
                params.put("dateCompleted", "2019-09-21");
                params.put("puzzleName", "Medium3");
                params.put("difficultyID", "2");
                params.put("difficultyName", "Medium");


                return params;
            }
        };
        mQueue.add(request);

    }
}

//        "completed": 1,
//        "userName": "Turbopulsa",
//        "active": 1,
//        "puzzleID": 9,
//        "userID": 12,
//        "dateStarted": "2019-09-21",
//        "dateCompleted": "2019-09-21",
//        "puzzleName": "Medium3",
//        "difficultyID": 2,
//        "difficultyName": "Medium"
//        }

