package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Walking extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps,tv_stepdetector;
    private SensorManager sensorManager;
    private Sensor mStepCounter,mStepDetector;
    private boolean isCounterSensorPresent,isDetectorSensorPresent;
    int stepCount = 0, stepDetector = 0;
    ImageView iv_backarrow;
    ImageButton profile,ib_mealplans,ib_excercises,ib_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Walking.this, HomeScreen.class);
                finish();
            }
        });


        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        tv_steps = findViewById(R.id.tv_steps);
        tv_stepdetector = findViewById(R.id.tv_stepdetector);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            Toast.makeText(this, "Sensor detected", Toast.LENGTH_SHORT).show();
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        } else {
            tv_stepdetector.setText("Step Detector Sensor is not present");
            Toast.makeText(this, "Counter Sensor not found", Toast.LENGTH_SHORT).show();
            isCounterSensorPresent = false;
        }


        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            Toast.makeText(this, "Sensor detected", Toast.LENGTH_SHORT).show();
            mStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            isDetectorSensorPresent = true;
        } else {
            tv_stepdetector.setText("Step Detector Sensor is not present");
            Toast.makeText(this, "Detector Sensor not found", Toast.LENGTH_SHORT).show();
            isDetectorSensorPresent = false;
        }


        profile = findViewById(R.id.imgbtn_profile);
        ib_mealplans = findViewById(R.id.ib_mealplans);
        ib_excercises = findViewById(R.id.ib_excercises);
        ib_home = findViewById(R.id.ib_home);


        ib_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Walking.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        ib_excercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Walking.this, Exercises.class);
                startActivity(intent);
            }
        });



        ib_mealplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Walking.this, MealPlan.class);
                startActivity(intent);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Walking.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });





    }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor == mStepCounter) {
            sensorEvent.values[0] = 0;
            //stepCount = (int) sensorEvent.values[0];
            if (stepCount == 0) {
                tv_steps.setText("0");
            } else {
                tv_steps.setText(String.valueOf(stepCount));
            }

        } else if (sensorEvent.sensor == mStepDetector) {
            stepDetector = (int) (stepDetector + sensorEvent.values[0]);
            tv_stepdetector.setText(String.valueOf(stepDetector));
        }
        //Toast.makeText(this, ""+sensorEvent.sensor, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            sensorManager.registerListener(this, mStepDetector, SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @Override
    protected void onPause ( ) {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this,mStepCounter);
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            sensorManager.unregisterListener(this,mStepDetector);
        }


    }




}