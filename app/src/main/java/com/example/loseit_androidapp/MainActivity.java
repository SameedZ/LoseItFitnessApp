package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onesignal.OneSignal;


public class MainActivity extends AppCompatActivity {
    Button btn_start;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private static final String ONESIGNAL_APP_ID = "b89a8fec-28a4-4187-9d58-cbd2cf031f27";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();



        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // check if the user is already logged in
        if(mUser != null){
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            startActivity(intent);
            finish();
        }




        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Welcome1Screen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}