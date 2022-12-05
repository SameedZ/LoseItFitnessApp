package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class weightgoal extends AppCompatActivity {

    EditText et_weight;
    Button btn_next;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightgoal);

        et_weight = findViewById(R.id.et_idealweight);
        btn_next = findViewById(R.id.next);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if et_weight is empty
                if (et_weight.getText().toString().isEmpty()) {
                    // toast message
                    et_weight.requestFocus();

                }  else {
                    saveWeighttoDB();
                    Intent intent = new Intent(weightgoal.this,height.class);
                    startActivity(intent);
                    finish();
                }



            }

            private void saveWeighttoDB() {
                FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("idealweight").setValue(et_weight.getText().toString());
            }
        });




    }
}