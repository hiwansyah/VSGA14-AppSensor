package com.example.appsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;

    private TextView txtLight, txtProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtLight = findViewById(R.id.txtLight);
        txtProximity = findViewById(R.id.txtProximity);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        String no_sensor = getResources().getString(R.string.error_no_sensor);

        if (mSensorLight == null){
            txtLight.setText(no_sensor);
        }
        if (mSensorProximity == null){
            txtProximity.setText(no_sensor);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorLight != null){
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorProximity != null){
            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentvalue = event.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                txtLight.setText(getResources().getString(R.string.label_light, currentvalue));
                break;
            case Sensor.TYPE_PROXIMITY:
                txtProximity.setText(getResources().getString(R.string.label_proximity, currentvalue));
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}