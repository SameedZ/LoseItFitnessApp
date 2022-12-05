package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class EditProfile extends AppCompatActivity {

    ImageView iv_editprofile,iv_backarrow;
    EditText et_name, et_age, et_height, et_weight , et_email,et_gender;
    Button btn_save;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_editprofile =findViewById(R.id.iv_editprofile);
        et_email = findViewById(R.id.et_emailaddress);
        et_name = findViewById(R.id.et_fullname);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        et_weight = findViewById(R.id.et_weight);
        et_height = findViewById(R.id.et_height);

        btn_save = findViewById(R.id.btn_Save);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // check if mUser is signed in or not
        if(mUser == null){
            Intent intent = new Intent(EditProfile.this,LoginScreen.class);
            startActivity(intent);
            finish();
        } else {
            // get the mUser details from firebase realtime database
            // and set the details to the edit text fields
            getandpopulateuserdetails();
        }


        iv_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditProfile.this).start();
            }
        });

        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveuserdetails();
            }
        });



    }

    private void saveuserdetails() {

        String name = et_name.getText().toString();
        String age = et_age.getText().toString();


        if (et_name.getText().toString().isEmpty()){

            et_name.setError("Please enter your name");
            et_name.requestFocus();

        } else if (age.isEmpty()){
            et_age.setError("Please enter your age");
            et_age.requestFocus();

        } else if ( et_weight.getText().toString().isEmpty() ) {
            et_weight.setError("Please enter your weight");
            et_weight.requestFocus();

        } else if ( et_gender.getText().toString().isEmpty() ) {
            et_gender.setError("Please enter your gender");
            et_gender.requestFocus();
        } else if (et_height.getText().toString().isEmpty()) {
            et_height.setError("Please enter your height");
            et_height.requestFocus();

        } else {
            // save the user details to firebase realtime database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
            databaseReference.child(mUser.getUid()).child("name").setValue(name);
            databaseReference.child(mUser.getUid()).child("age").setValue(age);
            databaseReference.child(mUser.getUid()).child("weight").setValue(et_weight.getText().toString());
            databaseReference.child(mUser.getUid()).child("height").setValue(et_height.getText().toString());
            databaseReference.child(mUser.getUid()).child("gender").setValue(et_gender.getText().toString());



        }










    }

    private void getandpopulateuserdetails() {


       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid());
       ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot DataSnapshot) {
               for (DataSnapshot snapshot: DataSnapshot.getChildren()){
                   String key = snapshot.getKey();
                   String value = snapshot.getValue().toString();

                   Toast.makeText(EditProfile.this, "Key:"+key +"\tValue:"+value, Toast.LENGTH_SHORT).show();

                   if (key.equals("name")){
                        et_name.setText(value);
                   } else if (key.equals("email")){
                        et_email.setText(value);
                   } else if (key.equals("age")){
                        et_age.setText(value);
                   } else if (key.equals("height")){
                        et_height.setText(value);
                   } else if (key.equals("weight")){
                        et_weight.setText(value);
                   } else if (key.equals("gender")) {
                        et_gender.setText(value);
                   }


               }



           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       }) ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            //uploadImage(uri);


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }

    }







}