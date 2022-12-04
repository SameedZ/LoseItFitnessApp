package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class weightgoal extends AppCompatActivity {

    EditText et_weight;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightgoal);

        et_weight = findViewById(R.id.et_idealweight);
        btn_next = findViewById(R.id.next);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHeighttoDB();
                Intent intent = new Intent(weightgoal.this,height.class);
                startActivity(intent);
                finish();
            }

            private void saveHeighttoDB() {
                // send the volley request through here.....
                return ;
            }
        });




    }
}