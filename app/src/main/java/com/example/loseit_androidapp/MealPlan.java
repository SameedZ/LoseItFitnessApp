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
import android.widget.TextView;
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

public class MealPlan extends AppCompatActivity {
    Uri imageUri;
    ImageView iv_addmeal,iv_backarrow;
    ImageView breakFastBtn, lunchBtn, dinnerBtn;
    ArrayList<MyMeals> arrayList;
    ArrayList<MyMeals> breakFast;
    ArrayList<MyMeals> lunch;
    ArrayList<MyMeals> dinner;
    TextView mealCount;
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
        breakFast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();

        mealCount = findViewById(R.id.mealCount);
        breakFastBtn = findViewById(R.id.btnBreakFast);
        lunchBtn = findViewById(R.id.btnLunch);
        dinnerBtn = findViewById(R.id.btnDinner);


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

        DatabaseReference userExcercisesRef = FirebaseDatabase.getInstance().getReference("UserMeals");
        userExcercisesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    Log.i("children", "response: " + ds.getValue());
                    String userLodgedIn = mUser.getUid();
                    String currentuser = ds.child("userid").getValue() + "";
                    if(userLodgedIn.equals(currentuser)) {

                        String mealTitle = ds.child("name").getValue() + ". ";
                        String calories = ds.child("calories").getValue() + ".0";
                        String timeDuration = ds.child("type").getValue() + ". ";


                        MyMeals temp = new MyMeals(
                                R.drawable.meal_bg,
                                timeDuration + ".",
                                calories + " kcal",
                                mealTitle
                        );
                        arrayList.add(temp);

                    }

                }
                mealCount.setText(arrayList.size() + " Meals");
                MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(arrayList, MealPlan.this);
                recyclerView.setAdapter(mealPlanAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        breakFastBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        Log.i("children", "response: " + ds.getValue());
                        String userLodgedIn = mUser.getUid();
                        String currentuser = ds.child("userid").getValue() + "";
                        if(userLodgedIn.equals(currentuser)) {

                            String mealTitle = ds.child("name").getValue() + ". ";
                            String calories = ds.child("calories").getValue() + ".0";
                            String timeDuration = ds.child("type").getValue() + ". ";
                            if(timeDuration.toLowerCase(Locale.ROOT).equals("breakfast. ")) {
                                MyMeals temp = new MyMeals(
                                        R.drawable.meal_bg,
                                        timeDuration,
                                        calories + " kcal",
                                        mealTitle
                                );
                                breakFast.add(temp);
                            }

                        }

                    }
                    mealCount.setText(breakFast.size() + " Meals");
                    if(breakFast.size() == 0) {
                        Toast.makeText(MealPlan.this, "No meal found!", Toast.LENGTH_SHORT).show();
                    }
                    MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(breakFast, MealPlan.this);
                    recyclerView.setAdapter(mealPlanAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        });

        lunchBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        Log.i("children", "response: " + ds.getValue());
                        String userLodgedIn = mUser.getUid();
                        String currentuser = ds.child("userid").getValue() + "";
                        if(userLodgedIn.equals(currentuser)) {

                            String mealTitle = ds.child("name").getValue() + ". ";
                            String calories = ds.child("calories").getValue() + ".0";
                            String timeDuration = ds.child("type").getValue() + ". ";
                            if(timeDuration.toLowerCase(Locale.ROOT).equals("lunch. ")) {
                                MyMeals temp = new MyMeals(
                                        R.drawable.meal_bg,
                                        timeDuration,
                                        calories + " kcal",
                                        mealTitle
                                );
                                lunch.add(temp);
                            }

                        }

                    }
                    mealCount.setText(lunch.size() + " Meals");
                    if(lunch.size() == 0) {
                        Toast.makeText(MealPlan.this, "No meal found!", Toast.LENGTH_SHORT).show();
                    }
                    MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(lunch, MealPlan.this);
                    recyclerView.setAdapter(mealPlanAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        });

        dinnerBtn.setOnClickListener(view -> {
            userExcercisesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        Log.i("children", "response: " + ds.getValue());
                        String userLodgedIn = mUser.getUid();
                        String currentuser = ds.child("userid").getValue() + "";
                        if(userLodgedIn.equals(currentuser)) {

                            String mealTitle = ds.child("name").getValue() + ". ";
                            String calories = ds.child("calories").getValue() + ".0";
                            String timeDuration = ds.child("type").getValue() + ". ";
                            if(timeDuration.toLowerCase(Locale.ROOT).equals("dinner. ")) {
                                MyMeals temp = new MyMeals(
                                        R.drawable.meal_bg,
                                        timeDuration,
                                        calories + " kcal",
                                        mealTitle
                                );
                                dinner.add(temp);
                            }

                        }

                    }
                    mealCount.setText(dinner.size() + " Meals");
                    if(dinner.size() == 0) {
                        Toast.makeText(MealPlan.this, "No meal found!", Toast.LENGTH_SHORT).show();
                    }
                    MealPlanAdapter mealPlanAdapter = new MealPlanAdapter(dinner, MealPlan.this);
                    recyclerView.setAdapter(mealPlanAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        });



    }
}