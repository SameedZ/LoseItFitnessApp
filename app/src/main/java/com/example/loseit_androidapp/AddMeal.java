package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeal extends AppCompatActivity {

    EditText et_mealname, et_type, et_calories, et_description, et_category;
    Button btn_add;
    ImageView iv_backarrow;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
//    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // set all et's to their respective id's
        et_mealname = findViewById(R.id.et_mealname);
        et_type = findViewById(R.id.et_type);
        et_calories = findViewById(R.id.et_calories);
        et_description = findViewById(R.id.et_description);
        et_category = findViewById(R.id.et_category);
        btn_add = findViewById(R.id.btn_add);
        iv_backarrow = findViewById(R.id.iv_backarrow);


        // SQLite
//        DB = new DBHelper(this);

        // set onclick listener for btn_add
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatofirebase();

                // INSERTING DATA IN SQLite
//                boolean insertMealData = DB.insertNewMeal(mUser.getUid(),
//                        et_type.getText().toString(),
//                        et_mealname.getText().toString(),
//                        et_calories.getText().toString(),
//                        et_description.getText().toString()
//                        );
//                if(insertMealData == true) {
//                    Toast.makeText(AddMeal.this, et_mealname.getText().toString()
//                            + " Added Successfully.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(AddMeal.this, "Error in Adding meal.", Toast.LENGTH_SHORT).show();
//                }
            }
        });








        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create intent to move to HomeScreen
                Intent intent = new Intent(AddMeal.this, MealPlan.class);
                startActivity(intent);
                finish();
            }
        });






    }

    private void savedatatofirebase() {
        // check if all fields are filled
        if ( et_mealname.getText().toString().isEmpty() ) {
            et_mealname.requestFocus();
            et_mealname.setError("Please enter a meal name");
        } else if ( et_type.getText().toString().isEmpty() ) {
            et_type.requestFocus();
            et_type.setError("Please enter a meal type");
        } else if ( et_calories.getText().toString().isEmpty() ) {
            et_calories.requestFocus();
            et_calories.setError("Please enter a meal calorie amount");
        } else if ( et_description.getText().toString().isEmpty() ) {
            et_description.requestFocus();
            et_description.setError("Please enter a meal description");
        } else if ( et_category.getText().toString().isEmpty() ) {
            et_category.requestFocus();
            et_category.setError("Please enter a meal category");
        } else {


            // get the current time and date and set it to a string named "date"
            String date = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserMeals");
            databaseReference.child(mUser.getUid()+date).child("userid").setValue(mUser.getUid().toString());
            databaseReference.child(mUser.getUid()+date).child("time").setValue(date);
            databaseReference.child(mUser.getUid()+date).child("name").setValue(et_mealname.getText().toString());
            databaseReference.child(mUser.getUid()+date).child("type").setValue(et_type.getText().toString());
            databaseReference.child(mUser.getUid()+date).child("calories").setValue(et_calories.getText().toString());
            databaseReference.child(mUser.getUid()+date).child("description").setValue(et_description.getText().toString());
            databaseReference.child(mUser.getUid()+date).child("category").setValue(et_category.getText().toString());

            // show a toast that the meal has been added
//            Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show();



        }





    }
}