package com.example.loseit_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    ImageView editprofile,iv_backarrow;
    TextView tv_weight,tv_name,tv_height,tv_age;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_weight = findViewById(R.id.tv_weight);
        tv_name = findViewById(R.id.tv_name);
        tv_height = findViewById(R.id.tv_height);
        tv_age = findViewById(R.id.tv_age);

        tv_weight.setText("N");
        tv_name.setText("User Name");
        tv_height.setText("N");
        tv_age.setText("N");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // check if the mUser is logged in or not
        if(mUser == null){
            startActivity(new Intent(Profile.this,LoginScreen.class));
            finish();
        }  else {
            // get the mUser details from firebase realtime database
            // and set the details to the text views
            getandpopulateuserdetails();
        }



        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, HomeScreen.class);
                startActivity(intent);
            }
        });



        editprofile = findViewById(R.id.iv_editprofile);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,EditProfile.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void getandpopulateuserdetails() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot DataSnapshot) {
                for (DataSnapshot snapshot: DataSnapshot.getChildren()){
                    String key = snapshot.getKey();
                    String value = snapshot.getValue().toString();

                    //Toast.makeText(Profile.this, "Key:"+key +"\tValue:"+value, Toast.LENGTH_SHORT).show();

                    if (key.equals("name")){
                        tv_name.setText(value);
                    }  else if (key.equals("age")){
                        tv_age.setText(value);
                    } else if (key.equals("height")){
                        tv_height.setText(value);
                    } else if (key.equals("weight")){
                        tv_weight.setText(value);
                    }


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Error getting user details", Toast.LENGTH_SHORT).show();
            }
        }) ;


    }
}