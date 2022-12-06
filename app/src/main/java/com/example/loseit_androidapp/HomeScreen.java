package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class HomeScreen extends AppCompatActivity {

    ImageButton profile,ib_mealplans,ib_excercises;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        profile = findViewById(R.id.imgbtn_profile);
        ib_mealplans = findViewById(R.id.ib_mealplans);
        ib_excercises = findViewById(R.id.ib_excercises);

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





    }
}