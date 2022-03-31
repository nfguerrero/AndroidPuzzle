package com.example.onebuttonvolley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoJSONVolleyTest{

        @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


//        @Test
//        public void getResponseTest_returnsTrue() throws JSONException {
//
//
//            MainActivity testLeaderBoardPull = new MainActivity();
//
//
//            //We are expecting a JSONObject response from the server that represents whether or not the username matches the password
//            JSONObject response = new JSONObject();
//
//    /*In this simulated instance, the response from the server is a JSONObject "loginSuccess",
//    with a boolean value "true" because the login was a success
//     */
//            response.put("loginSuccess",new Boolean (true));
//
//
//            //when(test.getResponse(usernameCorrect,passwordCorrect)).thenReturn(response);
//
//            Assert.assertEquals(testLogInSuccess.tryLogin(usernameCorrect,passwordCorrect,test),response.getBoolean("loginSuccess"));
//        }
            @Test
        public void getResponseTest_returnsFalse() throws JSONException {
            RequestQueue mqueue = mock(RequestQueue.class);
            JsonArrayRequest request = mock(JsonArrayRequest.class);

                //We are expecting a JSONObject response from the server
            JSONArray response = new JSONArray();

    /*In this simulated instance, the response from the server is a JSONObject "Error",
    with a boolean value "false" because the volley failed
     */
            response.put("Error");

            when(mqueue.add(request)).thenReturn(null);

            Assert.assertEquals(mqueue.add(request),response.getBoolean(1));
        }

    }
