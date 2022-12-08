package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class WaterTracker extends AppCompatActivity {

    Button btn_add;
    ImageView iv_backarrow;
    TextView tv_todayswatercount,tv_totalglasses;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ImageButton profile,ib_mealplans,ib_excercises,ib_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);


        tv_totalglasses = findViewById(R.id.tv_totalglasses);
        tv_todayswatercount = findViewById(R.id.tv_todayswatercount);
        btn_add = findViewById(R.id.btn_add);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser == null) {
            Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show();
            // intetnt to login page
            Intent intent = new Intent(WaterTracker.this, LoginScreen.class);
            startActivity(intent);
            finish();
        } else {
            getUsersWaterData();
            getUsersWaterAllDataCount();
        }



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addglassofwater();
            }
        });
        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaterTracker.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
        profile = findViewById(R.id.imgbtn_profile);
        ib_mealplans = findViewById(R.id.ib_mealplans);
        ib_excercises = findViewById(R.id.ib_excercises);
        ib_home = findViewById(R.id.ib_home);

        ib_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaterTracker.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });


        ib_excercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaterTracker.this, Exercises.class);
                startActivity(intent);
            }
        });



        ib_mealplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaterTracker.this, MealPlan.class);
                startActivity(intent);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaterTracker.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void getUsersWaterAllDataCount() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("WaterConsumption");
        // I want to iterate over all the children of the WaterConsumption node and get the data for the current user
        // and the current date
        AtomicInteger counter = new AtomicInteger();
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    if ( snapshot.child("userid").getValue().equals(mUser.getUid())) {
                        // create a counter variable that will increment by 1
                        // for every glass of water the user has consumed

                        counter.getAndIncrement();
                        // set the text of the button to the counter variable
                        tv_totalglasses.setText(String.valueOf(counter));
                    }
                }
            }
        });


    }



    private void getUsersWaterData() {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("WaterConsumption");
        // I want to iterate over all the children of the WaterConsumption node and get the data for the current user
        // and the current date

        AtomicInteger counter = new AtomicInteger();
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    if (snapshot.child("date").getValue().equals(date) && snapshot.child("userid").getValue().equals(mUser.getUid())) {
                        // create a counter variable that will increment by 1
                        // for every glass of water the user has consumed

                        counter.getAndIncrement();
                        // set the text of the button to the counter variable
                        tv_todayswatercount.setText(String.valueOf(counter));
                    }
                }
            }
        });

    }

    private void sendGoalNotification() throws JSONException {
        // Create the notification payload.


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                return;
            }

            // Get new FCM registration token
            String fcm_token = task.getResult();
            Toast.makeText(WaterTracker.this, fcm_token, Toast.LENGTH_SHORT).show();
            JSONObject payload = new JSONObject();

            try {
                payload.put("notification", new JSONObject().put("title", "My Notification").put("body", "This is a test notification."));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Send the notification to the device with the given FCM token.
            FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(fcm_token)
                    .setMessageId("1").addData("data", payload.toString()).build());

        });





    }






    private void addglassofwater() {

        if (mUser != null) {
            // get today's date in java and save it as a string date
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("WaterConsumption");
            String waterid = databaseReference.push().getKey();
            databaseReference.child(waterid).child("userid").setValue(mUser.getUid()).toString();
            databaseReference.child(waterid).child("date").setValue(date);
            databaseReference.child(waterid).child("waterconsumed").setValue("1");

            Toast.makeText(this, "Congrats. Keep Going 🥛", Toast.LENGTH_SHORT).show();
            getUsersWaterData();
            getUsersWaterAllDataCount();
            try {
                sendGoalNotification();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Please Login to Continue", Toast.LENGTH_SHORT).show();
        }



    }


}