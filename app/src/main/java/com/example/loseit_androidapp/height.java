package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class height extends AppCompatActivity {
    Button btn_next;
    EditText et_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);

        et_height = findViewById(R.id.et_height);
        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveHeighttoDB();
                Intent intent = new Intent(height.this,Level.class);

                startActivity(intent);
                finish();
            }

            private void saveHeighttoDB() {
                /// send volley request here....
                return ;

            }
        });




    }
}