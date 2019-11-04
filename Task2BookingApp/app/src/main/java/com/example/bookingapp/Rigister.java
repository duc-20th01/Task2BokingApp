package com.example.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Rigister extends AppCompatActivity {
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
                member.setName(txtname.getText().toString());
                member.setEmail(txtemail.getText().toString());

                if (txtpassword.getText().length() < 8) {
                    Toast.makeText(Rigister.this, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (txtpassword.getText().equals(txtconfirm)) {
                    member.setPassword(txtpassword.getText().toString());
                } else{
                    Toast.makeText(Rigister.this, "Confirm password does not match password field", Toast.LENGTH_SHORT).show();
                    return;
                }

                member.setAddress(txtaddress.getText().toString());
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
                            Intent intent = new Intent(Rigister.this, Login.class);
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

}
