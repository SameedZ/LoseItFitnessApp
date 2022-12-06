package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MealPlan extends AppCompatActivity {
    Uri imageUri;
    ImageView iv_addmeal,iv_backarrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        iv_addmeal = findViewById(R.id.iv_addmeal);
        iv_backarrow = findViewById(R.id.backArrow);


        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealPlan.this, HomeScreen.class);
                startActivity(intent);
            }
        });





        iv_addmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MealPlan.this, AddMeal.class);
                startActivity(intent);
            }
        });




        RecyclerView recyclerView = findViewById(R.id.rv_mealplans);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyMeals[] myMeals = new MyMeals[] {
                new MyMeals(
                    R.drawable.meal_bg,
                        "10 min",
                        "20 cal",
                        "Dummy meal"
                ),
                new MyMeals(
                        R.drawable.meal_bg,
                        "30 min",
                        "20 cal",
                        "Dummy meal"
                ),new MyMeals(
                    R.drawable.meal_bg,
                        "50 min",
                        "20 cal",
                        "Dummy meal"
                ),

        };
        MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(myMeals, MealPlan.this);
        recyclerView.setAdapter(mealPlanAdapter);
    }
}