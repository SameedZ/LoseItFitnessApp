package com.example.loseit_androidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddExercise extends AppCompatActivity {
    ImageView iv_backarrow;

    EditText et_exercisename,et_duration,et_caloriesburnt,et_level,et_category,et_description;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ExerciseHelper DB;

    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // set all EditTexts to their respective ids
        et_exercisename = findViewById(R.id.et_exercisename);
        et_duration = findViewById(R.id.et_duration);
        et_caloriesburnt = findViewById(R.id.et_caloriesburnt);
        et_level = findViewById(R.id.et_level);
        et_category = findViewById(R.id.et_category);
        et_description = findViewById(R.id.et_description);

        // SQLite DB
        DB = new ExerciseHelper(this);

        // set all buttons to their respective ids
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatofirebase();
                boolean insertMealData = DB.insertNewExercise(mUser.getUid(),
                        et_exercisename.getText().toString(),
                        et_duration.getText().toString(),
                        et_caloriesburnt.getText().toString(),
                        et_category.getText().toString(),
                        et_description.getText().toString()
                        );
                if(insertMealData == true) {
                    Log.i("sql", "sql insertion: "+ et_exercisename.getText().toString());
                    Toast.makeText(AddExercise.this, et_exercisename.getText().toString()
                            + " Added Successfully.", Toast.LENGTH_SHORT).show();
                    Cursor res = DB.getExercises();
                    if(res.getCount() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (res.moveToNext()) {
                            stringBuffer.append("Exercise: " + res.getString(1));
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddExercise.this);
                        builder.setCancelable(true);
                        builder.setTitle("Exercise Added");
                        builder.setMessage(stringBuffer.toString());
                        builder.show();
                    }


                } else {
                    Toast.makeText(AddExercise.this, "Error in Adding meal.", Toast.LENGTH_SHORT).show();
                }



            }
        });

        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExercise.this, Exercises.class);
                startActivity(intent);
            }
        });



    }

    private void savedatatofirebase() {

        if (et_exercisename.getText().toString().isEmpty()) {
            et_exercisename.setError("Please enter exercise name");
            et_exercisename.requestFocus();
            return;
        } else if (et_duration.getText().toString().isEmpty()) {
            et_duration.setError("Please enter duration");
            et_duration.requestFocus();
            return;
        } else if (et_caloriesburnt.getText().toString().isEmpty()) {
            et_caloriesburnt.setError("Please enter calories burnt");
            et_caloriesburnt.requestFocus();
            return;
        } else if (et_level.getText().toString().isEmpty()) {
            et_level.setError("Please enter level");
            et_level.requestFocus();
            return;
        } else if (et_category.getText().toString().isEmpty()) {
            et_category.setError("Please enter category");
            et_category.requestFocus();
            return;
        } else if (et_description.getText().toString().isEmpty()) {
            et_description.setError("Please enter description");
            et_description.requestFocus();
            return;
        } else {
            // save data to firebase
            // get the current time and date and set it to a string named "date"
            String date = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UserExcercises");

            // create a uniqe excercise id
            String exerciseid = databaseReference.push().getKey();
            databaseReference.child(exerciseid).child("userid").setValue(mUser.getUid().toString());
            databaseReference.child(exerciseid).child("time").setValue(date);
            databaseReference.child(exerciseid).child("exercisename").setValue(et_exercisename.getText().toString());
            databaseReference.child(exerciseid).child("duration").setValue(et_duration.getText().toString());
            databaseReference.child(exerciseid).child("caloriesburnt").setValue(et_caloriesburnt.getText().toString());
            databaseReference.child(exerciseid).child("level").setValue(et_level.getText().toString());
            databaseReference.child(exerciseid).child("category").setValue(et_category.getText().toString());
            databaseReference.child(exerciseid).child("description").setValue(et_description.getText().toString());

            // show a toast that the meal has been added
            Toast.makeText(this, "Excercise added", Toast.LENGTH_SHORT).show();


        }


    }
}