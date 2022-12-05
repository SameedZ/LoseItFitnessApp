package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

public class MealPlan extends AppCompatActivity {
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

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