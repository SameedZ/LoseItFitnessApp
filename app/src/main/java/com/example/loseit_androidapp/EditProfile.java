package com.example.loseit_androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;


public class EditProfile extends AppCompatActivity {

    ImageView iv_editprofile;
    String url_imgupload = URLHelper.server_url+"uploaduserprofileimg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        iv_editprofile =findViewById(R.id.iv_editprofile);

        iv_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditProfile.this).start();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            uploadImage(uri);


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }

    }


    public void uploadImage(Uri uri){


        // call to action on the login button
        StringRequest request = new StringRequest(Request.Method.POST, url_imgupload, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditProfile.this, response, Toast.LENGTH_SHORT).show();
                iv_editprofile.setImageURI(uri);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EditProfile.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        )  {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("photo",uri.toString());
                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(EditProfile.this);
        rq.add(request);



    } // login ends here






}