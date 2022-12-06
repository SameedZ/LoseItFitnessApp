package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Exercises extends AppCompatActivity {
    Button addExcerciseBtn;
    ImageView backArrow;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        populateUserAllExcercises();



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

    private void populateUserAllExcercises() {

        // create a java string list
        ArrayList<String> listexcerciseid = new ArrayList<>();

        Toast.makeText(Exercises.this, "populateUserAllExcercises", Toast.LENGTH_SHORT).show();
        // get a reference to UserExcercises
        DatabaseReference userExcercisesRef = FirebaseDatabase.getInstance().getReference("UserExcercises");
        // UserExcercises -> Excercise ID -> User ID
        userExcercisesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String excerciseID = ds.getKey();
                    Toast.makeText(Exercises.this, excerciseID, Toast.LENGTH_SHORT).show();
                    // each excerciseID contains a userid key and a value against it. check if our current user id matches userid value
                    if (ds.child(mUser.getUid()).exists()) {
                        // if it matches, add the excercise id to the list
                        // also toast the excercise id


                        //ds.child("caloriesburnt").getValue();
                        Toast.makeText(Exercises.this, excerciseID+"found", Toast.LENGTH_SHORT).show();
                        listexcerciseid.add(excerciseID);
                    }
                    // add the excercise ID to the list
                    listexcerciseid.add(excerciseID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










    }

}