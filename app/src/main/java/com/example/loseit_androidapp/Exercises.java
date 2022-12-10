package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Locale;
import java.util.Objects;

public class Exercises extends AppCompatActivity {
    Button addExcerciseBtn,btn_walktracker;
    ImageView backArrow;
    Button allExercisesBtn;
    Button legsBtn;
    Button backBtn;
    Button chestBtn;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ArrayList<MyExercises> arrayList;
    ArrayList<MyExercises> legsExercises;
    ArrayList<MyExercises> chestExercises;
    ArrayList<MyExercises> backExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        arrayList = new ArrayList<>();
        legsExercises = new ArrayList<>();
        chestExercises = new ArrayList<>();
        backExercises = new ArrayList<>();
        legsBtn = findViewById(R.id.legsBtn);
        chestBtn = findViewById(R.id.chestBtn);
        backBtn = findViewById(R.id.backBtn);
        allExercisesBtn = findViewById(R.id.allEx);


        String userLodgedIn = mUser.getUid();


        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercises.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        btn_walktracker = findViewById(R.id.btn_walktracker);
        btn_walktracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercises.this, Walking.class);
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

        DatabaseReference userExcercisesRef = FirebaseDatabase.getInstance().getReference("UserExcercises");
        // UserExcercises -> Excercise ID -> User ID
        userExcercisesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String excerciseID = ds.getKey();
//                    Toast.makeText(Exercises.this, excerciseID, Toast.LENGTH_SHORT).show();

                    String userLodgedIn = mUser.getUid();
                    String currentuser = ds.child("userid").getValue().toString();
                    if(userLodgedIn.equals(currentuser)) {
//                        Toast.makeText(Exercises.this, "hi", Toast.LENGTH_SHORT).show();

                        String exerciseTitle = Objects.requireNonNull(ds.child("exercisename").getValue()).toString();
                        String calories = Objects.requireNonNull(ds.child("caloriesburnt").getValue()).toString();
                        String timeDuration = Objects.requireNonNull(ds.child("duration").getValue()).toString();
                        String catagory = Objects.requireNonNull(ds.child("category").getValue()).toString();

                        MyExercises temp = new MyExercises(
                                exerciseTitle,
                                calories + " kcal",
                                timeDuration + " min",
                                catagory,
                                R.drawable.card2_ex
                        );
                        arrayList.add(temp);
//                        Log.i("children", "response: " + temp.getExerciseName());

                    }
                    MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(arrayList, Exercises.this);
                    recyclerView.setAdapter(myExercisesAdapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        legsBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String currentuser = Objects.requireNonNull(ds.child("userid").getValue()).toString();
                        if(userLodgedIn.equals(currentuser)) {
                            String exerciseTitle = Objects.requireNonNull(ds.child("exercisename").getValue()).toString();
                            String calories = Objects.requireNonNull(ds.child("caloriesburnt").getValue()).toString();
                            String timeDuration = Objects.requireNonNull(ds.child("duration").getValue()).toString();
                            String catagory = Objects.requireNonNull(ds.child("category").getValue()).toString();

                            if(Objects.requireNonNull(ds.child("category").getValue()).toString()
                                    .toLowerCase(Locale.ROOT).equals("legs")) {

                                MyExercises temp = new MyExercises(
                                        exerciseTitle,
                                        calories + " kcal",
                                        timeDuration + " min",
                                        catagory,
                                        R.drawable.card2_ex
                                );
//                                Log.i("ex-", "i-th exercise: " + temp.getExercisePlan());
                                legsExercises.add(temp);

                            }
                        }

                    }
                    if(legsExercises.size() == 0) {
                        Toast.makeText(Exercises.this, "No Exercise Found.", Toast.LENGTH_SHORT).show();
                    }
                    MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(legsExercises, Exercises.this);
                    recyclerView.setAdapter(myExercisesAdapter);

//
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
            legsExercises.clear();
        });

        chestBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String currentuser = Objects.requireNonNull(ds.child("userid").getValue()).toString();
                        if(userLodgedIn.equals(currentuser)) {
                            String exerciseTitle = Objects.requireNonNull(ds.child("exercisename").getValue()).toString();
                            String calories = Objects.requireNonNull(ds.child("caloriesburnt").getValue()).toString();
                            String timeDuration = Objects.requireNonNull(ds.child("duration").getValue()).toString();
                            String catagory = Objects.requireNonNull(ds.child("category").getValue()).toString();

                            if(Objects.requireNonNull(ds.child("category").getValue()).toString()
                                    .toLowerCase(Locale.ROOT).equals("chest")) {

                                MyExercises temp = new MyExercises(
                                        exerciseTitle,
                                        calories + " kcal",
                                        timeDuration + " min",
                                        catagory,
                                        R.drawable.card2_ex
                                );
//                                Log.i("ex-", "i-th exercise: " + temp.getExercisePlan());
                                chestExercises.add(temp);

                            }
                        }

                    }
                    if(chestExercises.size() < 1) {
                        Toast.makeText(Exercises.this, "No Exercise Found.", Toast.LENGTH_SHORT).show();
                    } else {
                        MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(chestExercises, Exercises.this);
                        recyclerView.setAdapter(myExercisesAdapter);
                    }
//
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
            chestExercises.clear();
        });

        backBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String currentuser = Objects.requireNonNull(ds.child("userid").getValue()).toString();
                        if(userLodgedIn.equals(currentuser)) {
                            String exerciseTitle = Objects.requireNonNull(ds.child("exercisename").getValue()).toString();
                            String calories = Objects.requireNonNull(ds.child("caloriesburnt").getValue()).toString();
                            String timeDuration = Objects.requireNonNull(ds.child("duration").getValue()).toString();
                            String catagory = Objects.requireNonNull(ds.child("category").getValue()).toString();

                            if(Objects.requireNonNull(ds.child("category").getValue()).toString()
                                    .toLowerCase(Locale.ROOT).equals("back")) {

                                MyExercises temp = new MyExercises(
                                        exerciseTitle,
                                        calories + " kcal",
                                        timeDuration + " min",
                                        catagory,
                                        R.drawable.card2_ex
                                );
//                                Log.i("ex-", "i-th exercise: " + temp.getExercisePlan());
                                backExercises.add(temp);

                            }
                        }

                    }
                    if(backExercises.size() < 1) {
                        Toast.makeText(Exercises.this, "No Exercise Found.", Toast.LENGTH_SHORT).show();
                    }
                    MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(backExercises, Exercises.this);
                    recyclerView.setAdapter(myExercisesAdapter);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
            backExercises.clear();
        });

        allExercisesBtn.setOnClickListener(view -> {
            MyExercisesAdapter myExercisesAdapter = new MyExercisesAdapter(arrayList, Exercises.this);
            recyclerView.setAdapter(myExercisesAdapter);
        });


    }

}