package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Weight extends AppCompatActivity {

    EditText et_weight;
    Button btn_next;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        et_weight = findViewById(R.id.et_weight);
        btn_next = findViewById(R.id.btn_next);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if (et_weight.getText().toString().isEmpty()) {
                   // toast message
                    Toast.makeText(Weight.this, "Please Enter your weight", Toast.LENGTH_SHORT).show();
                    et_weight.requestFocus();

                }  else {
                    saveWeighttoDB();
                    Intent intent = new Intent(Weight.this,weightgoal.class);
                    startActivity(intent);
                    finish();
                }

            
            
            }

            private void saveWeighttoDB() {
               // save the weight to the FirebaseDatabase
                FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("weight").setValue(et_weight.getText().toString());

            }
        });





    }
}