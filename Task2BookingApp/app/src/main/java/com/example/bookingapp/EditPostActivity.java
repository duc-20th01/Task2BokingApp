package com.example.bookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPostActivity extends AppCompatActivity {
    Button btnOk, btnHome;
    EditText txtName, txtSkill, txtDescription, txtQuantity;
    Post post;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        init();
        addEvents();
    }

    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidInput()) {
                    post.setName(txtName.getText().toString());
                    post.setSkill(txtSkill.getText().toString());
                    post.setDescription(txtDescription.getText().toString());
                    post.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
                    apiService.updatePost(post).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            switch (response.code()) {
                                case 200:
                                    Toast.makeText(EditPostActivity.this, "Update Post Successfully!", Toast.LENGTH_SHORT).show();
                                    break;
                                case 204:
                                    Toast.makeText(EditPostActivity.this, "Update Post Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("EPE", "onFailure: " + t.getMessage());
                            Toast.makeText(EditPostActivity.this, "Update Post Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        txtName = findViewById(R.id.txtEditName);
        txtSkill = findViewById(R.id.txtEditSkill);
        txtDescription = findViewById(R.id.txtEditDescription);
        txtQuantity = findViewById(R.id.txtEditQuantity);
        btnOk = findViewById(R.id.btnOk);
        btnHome = findViewById(R.id.btnHome);

        post = (Post) getIntent().getSerializableExtra("edit_post");
        txtName.setText(post.getName());
        txtSkill.setText(post.getSkill());
        txtDescription.setText(post.getDescription());
        txtQuantity.setText(String.valueOf(post.getQuantity()));

        apiService = APIUtils.getServer();

    }

    private boolean checkValidInput(){
        String name = txtName.getText().toString();
        String skill = txtSkill.getText().toString();
        String description = txtDescription.getText().toString();
        String quantity = txtQuantity.getText().toString();

        if (name.length() == 0 || skill.length() == 0 || description.length() ==0 || quantity.length() ==0){
            Toast.makeText(this, "Please enter all fields completely!", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            if (Integer.valueOf(quantity) > 255){
                Toast.makeText(this, "Max quantity is 255", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
