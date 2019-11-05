package com.example.bookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText txtemail;
    private EditText txtpassword;
    private CheckBox checkRemember;
    private Button btnsign, btnexit;
    private FloatingActionButton fabAddAccount;
    private APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = APIUtils.getServer();
        initControls();
        addEvents();
        loadRemember();
    }

    private void addEvents() {
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txtemail.getText().toString();
                String Password = txtpassword.getText().toString();
                if (Email.length()==0){
                    Toast.makeText(Login.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()==0){
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                apiService.login(new Member(Email, Password)).enqueue(new Callback<Member>()
                {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        if ( response.body()!=null){
                            setRemember();
                            Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                            Member mb = response.body();
                            SharedPreferences sharedPreferences = getSharedPreferences("memberData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("id",mb.getId());
                            editor.putString("name",mb.getName());
                            editor.putString("email", mb.getEmail());
                            editor.putString("password", mb.getPassword());
                            editor.putString("address",mb.getAddress());
                            editor.putString("phone",mb.getPhone());
                            editor.putInt("type",mb.getType());
                            editor.commit();
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Login.this, "Email or password you've entered is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {
                        btnsign.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {

                                return false;
                            }
                        });

                    }
                });

            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
        fabAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }

    private void loadRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        boolean rememberChecked = sharedPreferences.getBoolean("REMEMBER_ME", false);
        if (rememberChecked){
            String email = sharedPreferences.getString("EMAIL", "default");
            String password = sharedPreferences.getString("PASSWORD", "default");
            checkRemember.setChecked(true);
            txtemail.setText(email);
            txtpassword.setText(password);
        }
    }

    private void setRemember() {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (checkRemember.isChecked()){
            String email = txtemail.getText().toString();
            String password = txtpassword.getText().toString();
            editor.putBoolean("REMEMBER_ME",  true);
            editor.putString("EMAIL", email);
            editor.putString("PASSWORD", password);
            editor.commit();
        }else {
            editor.putBoolean("REMEMBER_ME", false);
            editor.remove("EMAIL");
            editor.remove("PASSWORD");
            editor.commit();
        }
    }

    private void initControls() {
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        checkRemember = findViewById(R.id.checkBox);
        btnsign = findViewById(R.id.btnsignin);
        btnexit = findViewById(R.id.btnexit);
        fabAddAccount = findViewById(R.id.floatingActionButton);
    }
}
