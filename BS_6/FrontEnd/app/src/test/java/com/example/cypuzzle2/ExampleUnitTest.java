package com.example.cypuzzle2;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getResponseTest_returnsFalse() throws JSONException {
        RequestQueue mqueue = mock(RequestQueue.class);
        JsonArrayRequest request = mock(JsonArrayRequest.class);

        //We are expecting a JSONObject response from the server
        JSONArray response = new JSONArray();

    /*In this simulated instance, the response from the server is a JSONObject "Error",
    with a boolean value "false" because the volley failed
     */
        response.put(null);

        when(mqueue.add(request)).thenReturn(null);

        Assert.assertEquals(mqueue.add(request),response.get(response.length()-1));
    }

}