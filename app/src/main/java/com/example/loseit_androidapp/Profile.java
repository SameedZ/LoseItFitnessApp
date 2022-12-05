package com.example.loseit_androidapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loseit_androidapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    ImageView editprofile,iv_backarrow;
    CircleImageView profileImage;
    TextView tv_weight,tv_name,tv_height,tv_age;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseStorage firebaseStorage;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_weight = findViewById(R.id.tv_weight);
        tv_name = findViewById(R.id.tv_name);
        tv_height = findViewById(R.id.tv_height);
        tv_age = findViewById(R.id.tv_age);
        profileImage = findViewById(R.id.avatarpic);

        tv_weight.setText("N");
        tv_name.setText("User Name");
        tv_height.setText("N");
        tv_age.setText("N");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();

        // check if the mUser is logged in or not
//        if(mUser == null){
//            startActivity(new Intent(Profile.this,LoginScreen.class));
//            finish();
//        }  else {
//            // get the mUser details from firebase realtime database
//            // and set the details to the text views
//            getandpopulateuserdetails();
//        }


        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        // uploading profile image
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage.launch("image/*");

                uploadImage();
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

    private void uploadImage() {
        if(imageUri != null) {
            StorageReference storageReference = firebaseStorage.getReference().
                    child("images/" + mAuth.toString());
            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                     if(task.isSuccessful()) {
                         Toast.makeText(Profile.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(Profile.this, "Failed to upload image.", Toast.LENGTH_SHORT).show();
                     }
                }
            });

        }
    }

    // for image upload!

    ActivityResultLauncher<String> getImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null) {
                        profileImage.setImageURI(result);
                        imageUri = result;
                    }
                }
            }
    );

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