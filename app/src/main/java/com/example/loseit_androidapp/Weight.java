package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Weight extends AppCompatActivity {

    EditText et_weight;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        et_weight = findViewById(R.id.et_weight);
        btn_next = findViewById(R.id.btn_next);



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveWeighttoDB();
                Intent intent = new Intent(Weight.this,weightgoal.class);
                startActivity(intent);
                finish();
            }

            private void saveWeighttoDB() {
                // make volley post request here...
                return ;

            }
        });





    }
}