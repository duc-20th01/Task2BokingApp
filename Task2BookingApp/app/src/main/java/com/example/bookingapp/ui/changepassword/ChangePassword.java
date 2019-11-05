package com.example.bookingapp.ui.changepassword;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingapp.MainActivity;
import com.example.bookingapp.R;
import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.util.ProgressDialogF;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends Fragment {

    private ChangePasswordViewModel mViewModel;
    private EditText txtCurrentPwd;
    private EditText txtNewPwd;
    private EditText txtConfirmPwd;
    private Button btnHome;
    private Button btnChange;
    private APIService apiService;
    private Member member   ;
    private String temp;
    private Member mb;
    public static ChangePassword newInstance() {
        return new ChangePassword();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_password, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        apiService = APIUtils.getServer();
        //final Member mb = (Member) getActivity().getIntent().getSerializableExtra("");
        mViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        // TODO: Use the ViewModel
        addEvent();
    }

    private void addEvent(){
        mb = (Member) getActivity().getIntent().getSerializableExtra("key");
        txtCurrentPwd = getView().findViewById(R.id.txtCurrentPwd);
        txtNewPwd = getView().findViewById(R.id.txtNewPwd);
        txtConfirmPwd = getView().findViewById(R.id.txtConfirmPwd);
        btnHome = getView().findViewById(R.id.btnHome);
        btnChange =getView().findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepwd();

            }
            private void changepwd()
            {

                String currentpwd =txtCurrentPwd.getText().toString();

                if (currentpwd.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter current password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(member == null) {
                    String currentPwd = mb.getPassword();
                    if(currentPwd.equals(currentpwd)) {
                        String newPwd = txtNewPwd.getText().toString();
                        String comfirmPwd = txtConfirmPwd.getText().toString();
                        if (!checkPasswordlength(newPwd)) {
                            Toast.makeText(getContext(), "New password must contain at least 8 characters!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(currentPwd.equals(newPwd)){
                            Toast.makeText(getContext(), "New password can not be the same as your current password!", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if (newPwd.equals(comfirmPwd)) {
                            //temp =newPwd;
                            //changePwd.setPassword(newPwd);
                            UpdatePWD(new Member(mb.getId(),newPwd));
                            temp=newPwd;

                        } else {
                            Toast.makeText(getContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(getContext(), "Current password is incorrect, please try again!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean checkPasswordlength(String password){
        if (password.length() < 8)
            return false;
        return true;
    }

    public void UpdatePWD(final Member member) {
        apiService.changePassWord(member).enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ProgressDialogF.hideLoading();
                Log.d("add", response.body());

                if (response.body().equals("success")) {
                    Toast.makeText(getContext(), "Change password successfully!", Toast.LENGTH_LONG).show();
                    mb.setPassword(temp);

                } else {
                    Toast.makeText(getContext(), "Unable to change password!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("add", t.getMessage());
                ProgressDialogF.hideLoading();
                Toast.makeText(getContext(), "Change password failed! An error has occurred!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
