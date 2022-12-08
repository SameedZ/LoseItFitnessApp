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
import java.util.Objects;

public class Exercises extends AppCompatActivity {
    Button addExcerciseBtn,btn_walktracker;
    ImageView backArrow;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    MyExercises[] myExercises;
    ArrayList<MyExercises> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        arrayList = new ArrayList<>();
//        populateUserAllExcercises();


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

                        String exerciseTitle = ds.child("exercisename").getValue().toString();
                        String calories = ds.child("caloriesburnt").getValue().toString();
                        String timeDuration = ds.child("duration").getValue().toString();
                        String catagory = ds.child("category").getValue().toString();

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


//        myExercises = arrayList.toArray(new MyExercises[0]);


    }

}