package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUpScreen extends AppCompatActivity {

    EditText et_email,et_password;
    Button btn_signup;
    String emailPattern = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";

    String url = URLHelper.server_url+"users/createuser";

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_signup);
        
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupProcess();
            }
        });




    }



    private void SignupProcess() {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        boolean isValid = false;

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUpScreen.this, "Sign Up Complete.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpScreen.this,Weight.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignUpScreen.this, "err"+task.getException() , Toast.LENGTH_SHORT).show();
                }
            }

        });





    }



    public void navigatetologinpage(View view){
        Intent intent = new Intent(SignUpScreen.this,LoginScreen.class);
        startActivity(intent);
        finish();
    }

}