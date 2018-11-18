package com.example.jdmon.pacemanapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView x;
    private TextView y;

    private ImageView pacman;

    private SensorManager sensor_manager;
    private Client jugador;

    String ip;
    int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        ip = getIntent().getStringExtra("ip");
        port = 	Integer.parseInt(getIntent().getStringExtra("port"));

        sensor_manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        pacman = (ImageView) findViewById(R.id.pacman);
        pacman.setImageDrawable(getResources().getDrawable(R.drawable.pacman));


        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);

        connect_server();
        sleep(500);
    }

    /**
     * Crea la conexion con el servidor.
     */
    private void connect_server() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                jugador = new Client();
                jugador.set_ip(ip);
                jugador.set_port(port);
                jugador.start();
            }
        });
    }

    public void onResume(){
        super.onResume();
        sensor_manager.registerListener(this, sensor_manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void onStop(){
        sensor_manager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE){
            return;
        }

        try {
            jugador.send_data(JSONHandler.set_coords(event.values[0], event.values[1]));
            x.setText(Float.toString(event.values[0]));
            y.setText(Float.toString(event.values[1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}