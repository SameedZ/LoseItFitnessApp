package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Welcome1Screen extends AppCompatActivity {
    TextView tv_next,tv_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome1_screen);
        tv_next = findViewById(R.id.tv_next);
        tv_skip = findViewById(R.id.tv_skip);

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome1Screen.this,Welcome2Screen.class);
                startActivity(intent);
                finish();

            }
        });


    }
}