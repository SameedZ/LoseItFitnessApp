package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Level extends AppCompatActivity {

    ImageView beg,inter,adv;
    Button btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        btn_skip = findViewById(R.id.btn_skip);

        beg = findViewById(R.id.iv_begineer);
        inter = findViewById(R.id.iv_intermediate);
        adv = findViewById(R.id.iv_advanced);

        beg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // saveGoaltoDB("begineer");
                movetonextactivity();
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveGoaltoDB("intermediate");
                movetonextactivity();
            }
        });

        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveGoaltoDB("advanced");
                movetonextactivity();
            }
        });


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetonextactivity();

            }
        });





    }


    public void movetonextactivity(){
        Intent intent = new Intent(Level.this,goal.class);
        startActivity(intent);
        //finish();
    }



}