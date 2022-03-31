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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    private Button loginButton;
    private Button skipButton;


    private EditText email;
    private EditText password;
    private String UserName;
    private int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        mQueue = Volley.newRequestQueue(this);
        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.inputEmailForLogin);
        password = findViewById(R.id.inputPasswordForLogin);



        skipButton = findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                skipToMain();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginButton.setEnabled(!password.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volley();
            }
        });
    }



    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("UserName", UserName);
        intent.putExtra("Email", (String) email.getText().toString());
        intent.putExtra("UserID", UserID);
        startActivity(intent);
    }



    public void skipToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





    public void volley () {
        final String content = email.getText().toString();
        try {
            String url = "http://coms-309-bs-6.misc.iastate.edu:8080/users/email:" + content;
            JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject test = response.getJSONObject(0);
                        UserName = test.getString("userName");
                        UserID = test.getInt("userID");
                        Log.d("UserName", UserName);
                        String passwordReturned = test.getString("password");
                        String passwordString = password.getText().toString();
                        if (passwordString.equals(passwordReturned)) {
                            openMain();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_LONG);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_LONG);
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_LONG);
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Incorrect Email or Password",Toast.LENGTH_LONG);
            e.printStackTrace();
        }

    }
}
