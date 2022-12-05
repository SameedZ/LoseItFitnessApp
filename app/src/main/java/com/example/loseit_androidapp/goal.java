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

public class goal extends AppCompatActivity {

    ImageView lose, gain, improve;


    Button btn_skip;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        btn_skip = findViewById(R.id.btn_skip);

        lose = findViewById(R.id.iv_weightloss);
        gain = findViewById(R.id.iv_gainmuscle);
        improve = findViewById(R.id.iv_improvefitness);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        btn_skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveGoaltoDB("none");
                movetonextactivity();
            }

            private void movetonextactivity() {

                Intent intent = new Intent(goal.this, getstarted.class);
                startActivity(intent);
                finish();
            }
        });

        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("weightloss");
                movetonextactivity();
            }
        });

        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("gainmuscle");
                movetonextactivity();
            }
        });

        improve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGoaltoDB("improvefitness");
                movetonextactivity();
            }
        });



    }

    private void movetonextactivity() {

            Intent intent = new Intent(goal.this, getstarted.class);
            startActivity(intent);
            finish();
    }

    private void saveGoaltoDB(String goal_Value) {

        FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("goal").setValue(goal_Value);

    }
}