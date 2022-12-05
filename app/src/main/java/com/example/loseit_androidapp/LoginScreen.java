package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    EditText et_email,et_password;
    String str_email, str_password;

    Button btn_login;
    ImageView iv_back;
    TextView tv_view;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        et_email = findViewById(R.id.et_email);
        et_password =findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        iv_back = findViewById(R.id.iv_backarrow);

        str_email = et_email.getText().toString().trim();
        str_password = et_password.getText().toString().trim();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        // set on Clicker listener for moving back to screen main activity
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // check if the user is signed in or not
        if (mUser != null) {
            Toast.makeText(this, mUser.getEmail().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginScreen.this,HomeScreen.class);
            startActivity(intent);
            finish();
        } else {
            // if user is not signed in
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // check if the email and password is empty
                    if (et_email.getText().toString().isEmpty() || et_password.getText().toString().isEmpty()) {
                        // toast message
                        Toast.makeText(LoginScreen.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    } else {
                        // if email and password is not empty
                        // call the login method
                        loginInWithEmailandPassword();
                    }
                }
            });
        }

    }

    private void loginInWithEmailandPassword() {

        mAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // if login is successful
                // toast message
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                // move to home screen
                Intent intent = new Intent(LoginScreen.this,HomeScreen.class);
                startActivity(intent);
                finish();
            } else {
                // if login is not successful
                // toast message
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void navigatetosignuppage(View view){
        Intent intent = new Intent(LoginScreen.this,SignUpScreen.class);
        startActivity(intent);
        finish();
    }


}