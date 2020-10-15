package com.example.appsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private TextView sensor_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensor_list = findViewById(R.id.sensor_list);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorText = new StringBuilder();

        for (Sensor currentSensor : sensorList){
            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
        }
        sensor_list.setText(sensorText);
    }
    public void navigasi(View view){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}