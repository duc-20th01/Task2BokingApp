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
import android.widget.Toast;

import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostActivity extends AppCompatActivity {
    Button btnAdd, btnHome;
    EditText txtName, txtSkill, txtQuantity, txtDescription;
    APIService apiService;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initControls();
        addEvents();
    }

    private void addEvents() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidInput()){
                    Post post = new Post();
                    post.setName(txtName.getText().toString());
                    post.setSkill(txtSkill.getText().toString());
                    post.setDescription(txtDescription.getText().toString());
                    post.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
                    post.setAddress(member.getAddress());
                    post.setAuthorId(member.getEmail());
                    apiService.add(post).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body() == "true"){
                                Toast.makeText(NewPostActivity.this, "Posting is successful!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(NewPostActivity.this, "Post failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("NPE", "onFailure: " + t.getMessage() );
                            Toast.makeText(NewPostActivity.this, "Post failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initControls() {
        btnAdd = findViewById(R.id.btnAdd);
        btnHome = findViewById(R.id.btnHome);
        txtName = findViewById(R.id.txtNewPostName);
        txtSkill = findViewById(R.id.txtNewPostSkill);
        txtDescription = findViewById(R.id.txtNewPostDescripton);
        txtQuantity = findViewById(R.id.txtNewPostQuantity);
        apiService = APIUtils.getServer();
        loadMemberData();
    }

    private Member loadMemberData(){
        SharedPreferences sharedPreferences = getSharedPreferences("memberData", Context.MODE_PRIVATE);
        member = new Member();
        member.setId(sharedPreferences.getInt("id",0));
        member.setName(sharedPreferences.getString("name",""));
        member.setEmail(sharedPreferences.getString("email",""));
        member.setPassword(sharedPreferences.getString("password",""));
        member.setAddress(sharedPreferences.getString("address",""));
        member.setPhone(sharedPreferences.getString("phone",""));
        member.setType(sharedPreferences.getInt("type",1));
        return member;
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
