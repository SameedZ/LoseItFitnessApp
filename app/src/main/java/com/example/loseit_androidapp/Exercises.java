package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exercises extends AppCompatActivity {
    Button addExcerciseBtn;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercises.this, HomeScreen.class);
                startActivity(intent);
            }
        });


        addExcerciseBtn = findViewById(R.id.addExcerciseBtn);
        addExcerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exercises.this, AddExercise.class);
                startActivity(intent);
                finish();
            }
        });


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