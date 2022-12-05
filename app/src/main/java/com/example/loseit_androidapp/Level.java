package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Level extends AppCompatActivity {

    ImageView beg,inter,adv;
    Button btn_skip;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        btn_skip = findViewById(R.id.btn_skip);

        beg = findViewById(R.id.iv_begineer);
        inter = findViewById(R.id.iv_intermediate);
        adv = findViewById(R.id.iv_advanced);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        beg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("begineer");
                movetonextactivity();
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("intermediate");
                movetonextactivity();
            }
        });

        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("advanced");
                movetonextactivity();
            }
        });


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("null");
                movetonextactivity();
            }
        });





    }

    private void saveGoaltoDB(String fitnesslevel) {

        FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("fitnesslevel").setValue(fitnesslevel);

    }


    public void movetonextactivity(){
        Intent intent = new Intent(Level.this,goal.class);
        startActivity(intent);
        finish();
    }



}