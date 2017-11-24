package com.example.gonzalo.mobilecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.neovisionaries.ws.client.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.b1)
    Button b1;
    @BindView(R.id.b2)
    Button b2;
    @BindView(R.id.b3)
    Button b3;

    WebSocket ws;

    private static final String SERVER = "ws://websocket-claval-fibrolite.mybluemix.net/";
    private static final int TIMEOUT = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ws.sendText("COMMAND_0");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ws.sendText("COMMAND_1");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ws.sendText("COMMAND_2");
            }
        });

        try {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ws = connect(); //Realizar aquí tu proceso!
                        Log.d("MAIN", "CONECTADO");
                    } catch (Exception e) {
                        Log.e("Error", "Exception: " + e);
                    }
                }
            });

            thread.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static WebSocket connect() throws Exception {
        return new WebSocketFactory()
                .setConnectionTimeout(TIMEOUT)
                .createSocket(SERVER)
                .connect();
    }


}
