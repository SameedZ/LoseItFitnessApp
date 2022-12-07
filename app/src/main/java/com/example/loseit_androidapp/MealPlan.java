package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MealPlan extends AppCompatActivity {
    Uri imageUri;
    ImageView iv_addmeal,iv_backarrow;
    ArrayList<MyMeals> arrayList;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        iv_addmeal = findViewById(R.id.iv_addmeal);
        iv_backarrow = findViewById(R.id.backArrow);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        arrayList = new ArrayList<>();


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

//        MyMeals temp = new MyMeals(
//                R.drawable.meal_bg,
//                "Breakfast",
//                "20 kcal",
//                "Khaoo bas."
//        );
//        arrayList.add(temp);



        DatabaseReference userExcercisesRef = FirebaseDatabase.getInstance().getReference("UserMeals");
        userExcercisesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    Log.i("children", "response: " + ds.getValue());

                    MyMeals temp = new MyMeals(
                            R.drawable.meal_bg,
                            ds.child("type").getValue().toString(),
                            ds.child("calories").getValue().toString(),
                            ds.child("description").getValue().toString()
                    );

                    Log.i("children", "meal description: " + ds.child("description").getValue().toString());

                    arrayList.add(temp);
                    MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(arrayList, MealPlan.this);
                    recyclerView.setAdapter(mealPlanAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}