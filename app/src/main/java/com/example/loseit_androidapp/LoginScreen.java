package com.example.loseit_androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    EditText et_email,et_password;
    String str_email, str_password;

    Button btn_login;
    ImageView iv_back;


    String url = URLHelper.server_url+"users/createuser";


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






    }


    public void Login(View view){

        // call to action on the login button
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginScreen.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(LoginScreen.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        )  {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put("email",str_email);
                params.put("password",str_password);
                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(LoginScreen.this);
        rq.add(request);



    } // login ends here

    public void navigatetosignuppage(View view){
        Intent intent = new Intent(LoginScreen.this,SignUpScreen.class);
        startActivity(intent);
        finish();
    }


}