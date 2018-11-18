package com.example.jdmon.pacemanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button ip_button;
    private EditText ip;
    private EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_button = (Button) findViewById(R.id.button_ip);
        ip = (EditText) findViewById(R.id.ip);
        port = (EditText) findViewById(R.id.port);
    }

    public void onClick(View view) {
        Intent sensor_activity = new Intent(this, SensorActivity.class);
        sensor_activity.putExtra("ip", ip.getText().toString());
        sensor_activity.putExtra("port", port.getText().toString());
        Toast.makeText(MainActivity.this, ip.getText().toString(), Toast.LENGTH_SHORT).show();
        startActivity(sensor_activity);
    }
}