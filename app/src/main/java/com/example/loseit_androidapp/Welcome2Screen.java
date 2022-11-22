package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome2Screen extends AppCompatActivity {

    TextView tv_skip,tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2_screen);

        tv_skip = findViewById(R.id.tv_skip);
        tv_next = findViewById(R.id.tv_next);

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome2Screen.this,Welcome3Screen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}