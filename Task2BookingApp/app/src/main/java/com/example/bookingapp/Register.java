package com.example.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText txtname;
    private EditText txtemail;
    private EditText txtpassword;
    private EditText txtconfirm;
    private EditText txtaddress;
    private EditText txtphone;
    private Spinner spinner;
    private APIService apiService;
    private Button btnRigister;
    private Member member;
    private String list[] = {"School", "Company"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        init();
        addEvents();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_list_item, list);
        spinner.setAdapter(adapter);
    }

    public void init() {
        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        txtaddress = findViewById(R.id.txtaddress);
        txtphone = findViewById(R.id.txtphone);
        apiService = APIUtils.getServer();
        btnRigister = findViewById(R.id.btnRigister);
        txtconfirm = findViewById(R.id.con_txtpassword);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void addEvents() {
        btnRigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member = new Member();
                if (txtname.getText().length()==0){
                    Toast.makeText(Register.this, "Please enter your company/school name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                member.setName(txtname.getText().toString());
                if (!isValidEmail(txtemail.getText())){
                    Toast.makeText(Register.this, "Please enter your email correctly!", Toast.LENGTH_SHORT).show();
                    return;
                }
                member.setEmail(txtemail.getText().toString());
                if (txtpassword.getText().length() == 0)
                {
                    Toast.makeText(Register.this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (txtpassword.getText().length() < 8) {
                    Toast.makeText(Register.this, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }else if (txtconfirm.getText().length() < 8) {
                    Toast.makeText(Register.this, "Please confirm password!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (txtpassword.getText().toString().equals(txtconfirm.getText().toString()) ){
                    member.setPassword(txtpassword.getText().toString());
                } else{
                    Toast.makeText(Register.this, "Confirm password does not match password field", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtaddress.getText().length()==0){
                    Toast.makeText(Register.this, "Please enter your company/school address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                member.setAddress(txtaddress.getText().toString());
                if (txtphone.getText().length()==0 || txtphone.getText().length()<10){
                    Toast.makeText(Register.this, "Your phone number must be at least 10 digits!", Toast.LENGTH_SHORT).show();
                    return;
                }
                member.setPhone(txtphone.getText().toString());
                if (spinner.getSelectedItemId() == 0) {
                    member.setType(0);
                } else {
                    member.setType(1);
                }
                apiService.rigister(member).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("add", response.body());

                        if (response.body().equals("success")) {
                            Toast.makeText(getApplicationContext(), "You have successfully registered!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "You have failed registered!", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("add", t.getMessage());
                        Toast.makeText(getApplicationContext(), "The registration have failed! An error has occurred!", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
