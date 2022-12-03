package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        RecyclerView recyclerView = findViewById(R.id.rv_exercises);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyExercises[] myExercises = new MyExercises[] {
                new MyExercises(
                        "Exercise with jumping rope",
                        "10 kcal",
                        "20 min",
                        "Beginner",
                        R.drawable.card2_ex
                ),
                new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),new MyExercises(
                        "Jumping jacks",
                        "10 kcal",
                        "10 min",
                        "Beginner",
                        R.drawable.card1_ex
                ),
                new MyExercises(
                        "Squats",
                        "10 kcal",
                        "5 min",
                        "Beginner",
                        R.drawable.card2_ex
                )
        };

        MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(myExercises, Exercises.this);
        recyclerView.setAdapter(myExercisesAdapter);
    }
}