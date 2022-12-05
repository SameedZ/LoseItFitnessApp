package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class goal extends AppCompatActivity {

    Button btn_skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        btn_skip = findViewById(R.id.btn_skip);

        btn_skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                movetonextactivity();

            }

            private void movetonextactivity() {

                Intent intent = new Intent(goal.this, getstarted.class);
                startActivity(intent);
                finish();
            }
        });





    }
}