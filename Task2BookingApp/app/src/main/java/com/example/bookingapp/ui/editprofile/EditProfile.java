package com.example.bookingapp.ui.editprofile;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingapp.R;
import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.util.ProgressDialogF;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfile extends Fragment {

    private EditProfileViewModel mViewModel;
    private Spinner spinnerDanhSach;
    private EditText name,address,phone;
    private Button btSave;
    private Member mb,newMember = new Member();
    private String list[] = {"School", "Company"};
    private int tam;
    private APIService apiService;

    public static EditProfile newInstance() {
        return new EditProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        spinnerDanhSach = (Spinner)root.findViewById(R.id.spDoiTuong);
        name = (EditText) root.findViewById(R.id.edName);
        address = (EditText) root.findViewById(R.id.edAddress);
        phone = (EditText) root.findViewById(R.id.edPhone);
        btSave = (Button)root.findViewById(R.id.btSave);
        apiService = APIUtils.getServer();
        Member member = (Member) getActivity().getIntent().getSerializableExtra("key");
        /*ArrayList<String> list = new ArrayList<>();
        list.add("School");
        list.add("company");*/
        mb = setView(loadMemberData());
        saveall();
        return root;
    }

    private void saveall() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForChange()==true){
                    update(new Member(mb.getId(),newMember.getName(),newMember.getAddress(),newMember.getPhone(),newMember.getType()));
                    setMemberData(newMember);
                    //mb=setView();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    public Member setView(final Member member) {

        name.setText(member.getName());
        address.setText(member.getAddress());
        phone.setText(member.getPhone());
        if (member.getType() == 1) {
            String test = "School";
            list[0] = list[1];
            list[1] = test;
            tam = 1;
        } else {
            tam = 0;
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner_list_item, list);
        spinnerDanhSach.setAdapter(adapter);
        spinnerDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(list[i].equals("School")){
                    member.setType(0);
                }
                else{
                    member.setType(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return member;
    }
    public Boolean checkForChange(){
        this.newMember.setName(name.getText().toString());
        this.newMember.setAddress(address.getText().toString());
        this.newMember.setPhone(phone.getText().toString());
        this.newMember.setType(mb.getType());

        if(!mb.getName().equals(newMember.getName())||!mb.getAddress().equals(newMember.getAddress())||!mb.getPhone().equals(newMember.getPhone())||tam!=newMember.getType()){
            if(!newMember.getName().equals("")||!newMember.getAddress().equals("")||!newMember.getPhone().equals("")){
                return true;
            }
            else{
                Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        Toast.makeText(getActivity(), "Nội dung không mới", Toast.LENGTH_SHORT).show();
        return false;
    }
    private void update(Member member) {
        apiService.updateMember(member).enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ProgressDialogF.hideLoading();
                Log.d("updatemember", response.body());
                if (response.body().equals("success")) {
                    Toast.makeText(getActivity(), "Update content successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Update content failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("add", t.getMessage());
                ProgressDialogF.hideLoading();
                Toast.makeText(getActivity(), "Update content failed! An error has occurred!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private Member loadMemberData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("memberData", Context.MODE_PRIVATE);
        Member member = new Member();
        member.setId(sharedPreferences.getInt("id",0));
        member.setName(sharedPreferences.getString("name","default"));
        member.setAddress(sharedPreferences.getString("address","default"));
        member.setPhone(sharedPreferences.getString("phone","default"));
        member.setType(sharedPreferences.getInt("type",1));
        return member;
    }
    private void setMemberData(Member mb) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("memberData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",mb.getName());
        editor.putString("address",mb.getAddress());
        editor.putString("phone",mb.getPhone());
        editor.putInt("type",mb.getType());
        editor.commit();
    }
}
