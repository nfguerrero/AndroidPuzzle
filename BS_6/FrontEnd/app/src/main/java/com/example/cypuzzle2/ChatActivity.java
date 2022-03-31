package com.example.cypuzzle2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;


public class ChatActivity extends AppCompatActivity {
    private ImageButton button;
    Button  b1,b2;
    EditText e1,e2;
    ListView t1;
    ArrayList<String> arr;
    ArrayAdapter adapt;


    private WebSocketClient cc;
    //WebSocket ws = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        b1=(Button)findViewById(R.id.bt1);
        b2=(Button)findViewById(R.id.bt2);
        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        t1=(ListView)findViewById(R.id.tx1);
        arr=new ArrayList<>();
        adapt=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        t1.setAdapter(adapt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft [] drafts = {new Draft_6455()};

                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                //String w = "ws://10.26.13.93:8080/websocket/"+e1.getText().toString();
                //String w = "ws://"+"localhost:8080"+"/websocket/"+e1.getText().toString();
                String w= "ws://coms-309-bs-6.misc.iastate.edu:8080/websocket/"+e1.getText().toString();
                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            adapt.add(message);
                            Log.d("", "run() returned: " + message);


                            //String s=t1.getText().toString();
                            //t1.setText("hello world");
                            //Log.d("first", "run() returned: " + s);
                            //s=t1.getText().toString();
                            //Log.d("second", "run() returned: " + s);
                            //t1.setText(s+" Server:"+message);
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
                        }

                        @Override
                        public void onError(Exception e)
                        {
                            Log.d("Exception:", e.toString());
                        }
                    };
                }
                catch (URISyntaxException e) {
                    Log.d("Exception:", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(e2.getText().toString());
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });


        button = findViewById(R.id.chat_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openFriends();
            }
        });

    }

    public void openFriends(){
        //Intent intent = new Intent(this, FriendsActivity.class);
        //startActivity(intent);
        onBackPressed();
    }
}
