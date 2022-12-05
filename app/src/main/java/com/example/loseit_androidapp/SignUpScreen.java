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

import java.util.HashMap;
import java.util.Map;


public class SignUpScreen extends AppCompatActivity {

    EditText et_email,et_password;
    Button btn_signup;
    String emailPattern = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";

    String url = URLHelper.server_url+"users/createuser";

    FirebaseAuth mAuth;
    FirebaseUser mUser;



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
                Signup();
            }

            private void Signup() {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()){
                                      Toast.makeText(SignUpScreen.this, "Sign Up Complete.", Toast.LENGTH_SHORT).show();
                                      //SendUserInfoToWebServer();
                                      Intent intent = new Intent(SignUpScreen.this,Weight.class);
                                      startActivity(intent);
                                      finish();
                                  } else {
                                      Toast.makeText(SignUpScreen.this, "err"+task.getException() , Toast.LENGTH_SHORT).show();
                                  }
                        }

                        private void SendUserInfoToWebServer() {
                            // call to action on the login button
                            String email = et_email.getText().toString().trim();
                            String password = et_password.getText().toString().trim();

                            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(SignUpScreen.this, response , Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse (VolleyError error){
                                    Toast.makeText(SignUpScreen.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            )  {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> params = new HashMap<String,String>();

                                    params.put("email",email);
                                    params.put("password",password);
                                    return params;
                                }
                            };

                            RequestQueue rq = Volley.newRequestQueue(SignUpScreen.this);
                            rq.add(request);

                        }


                    });




            }


        });



    }






    public void navigatetologinpage(View view){
        Intent intent = new Intent(SignUpScreen.this,LoginScreen.class);
        startActivity(intent);
        finish();
    }

}