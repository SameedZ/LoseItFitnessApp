package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    ImageButton profile,ib_mealplans,ib_excercises;
    ImageView drawerloader;
    DrawerLayout profileDrawer;
    LinearLayout logout, brownDrawer;
    TextView btn_plans, btn_training, btn_myaccount, btn_signout, btn_categories, btn_settings,btn_myfavorites;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        profile = findViewById(R.id.imgbtn_profile);
        btn_plans = findViewById(R.id.btn_plans);
        btn_training = findViewById(R.id.btn_training);
        btn_categories = findViewById(R.id.btn_categories);
        btn_myaccount = findViewById(R.id.btn_myaccount);
        btn_myfavorites = findViewById(R.id.btn_myfavorites);
        btn_settings = findViewById(R.id.btn_appsettings);
        btn_signout = findViewById(R.id.btn_signout);
        ib_mealplans = findViewById(R.id.ib_mealplans);
        ib_excercises = findViewById(R.id.ib_excercises);
        drawerloader = findViewById(R.id.avatarpic);
        profileDrawer = findViewById(R.id.profileDrawer);
        brownDrawer = findViewById(R.id.sideDrawer);

        ib_excercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Exercises.class);
                startActivity(intent);
            }
        });



        ib_mealplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, MealPlan.class);
                startActivity(intent);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });



        brownDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "Working");
            }
        });



        drawerloader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileDrawer.isDrawerOpen(brownDrawer)) {
                    profileDrawer.closeDrawer(brownDrawer);
                } else {
                    profileDrawer.openDrawer(brownDrawer);
                }
            }
        });

        btn_plans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, MealPlan.class);
                startActivity(intent);
            }
        });

        btn_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Exercises.class);
                startActivity(intent);
            }
        });

        btn_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Category.class);
                startActivity(intent);
            }
        });

        btn_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, Profile.class);
                startActivity(intent);
            }
        });

        btn_myfavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, FavoritesEmpty.class);
                startActivity(intent);
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, AppSettings.class);
                startActivity(intent);
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });
    }
}