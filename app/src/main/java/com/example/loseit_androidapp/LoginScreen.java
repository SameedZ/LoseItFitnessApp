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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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



    FirebaseAuth mauth;
    FirebaseUser muser;


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

        // set on Clicker listener for moving back to screen main activity
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mauth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginScreen.this, "Sign In Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this,HomeScreen.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginScreen.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }



    public void navigatetosignuppage(View view){
        Intent intent = new Intent(LoginScreen.this,SignUpScreen.class);
        startActivity(intent);
        finish();
    }


}