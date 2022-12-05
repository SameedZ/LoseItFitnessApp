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

public class height extends AppCompatActivity {
    Button btn_next;
    EditText et_height;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        et_height = findViewById(R.id.et_height);
        btn_next = findViewById(R.id.btn_next);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if et_height is empty
                if (et_height.getText().toString().isEmpty()) {
                    // toast message
                    et_height.requestFocus();

                }  else {
                    saveHeighttoDB();
                    Intent intent = new Intent(height.this, Level.class);
                    startActivity(intent);
                    finish();
                }

            }

            private void saveHeighttoDB() {
                FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("height").setValue(et_height.getText().toString());
            }
        });




    }
}