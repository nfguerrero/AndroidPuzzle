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

public class ExampleUnit{

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Context mMockContext;

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


    @Test
    public void testGetReturn(){
        //Create a spy object of the class Calculator
        Calculator mockCalculator = Mockito.spy(new Calculator());
        //Return the value of 30 when the add method is called on the spied object with the arguments 10 and 20
        Mockito.doReturn(30).when(mockCalculator).add(10, 20);
        //Asserts that the return value of add method with arguments 10 and 20 is 30
        assertEquals(mockCalculator.add(10, 20), 30);
    }
}
