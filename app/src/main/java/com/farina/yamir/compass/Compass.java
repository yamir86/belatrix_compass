package com.farina.yamir.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

// implement SensorListener
public class Compass extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    static final int sensor = SensorManager.SENSOR_ORIENTATION;
    Sensor magnetSensor;
    Rose rose;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        rose = new Rose(this);

        setContentView(rose);

        // get sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        magnetSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // register to listen to sensors
    @Override
    public void onResume() {
        super.onResume();
    }

    // unregister
    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensor != Compass.sensor)
            return;
        int orientation = (int) event.values[0];
        rose.setDirection(orientation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}