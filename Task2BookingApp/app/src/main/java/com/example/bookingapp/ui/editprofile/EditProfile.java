package com.example.bookingapp.ui.editprofile;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookingapp.R;

import java.util.ArrayList;



public class EditProfile extends Fragment {

    private EditProfileViewModel mViewModel;
    private Spinner spinnerDanhSach;


    public static EditProfile newInstance() {
        return new EditProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        spinnerDanhSach = (Spinner)root.findViewById(R.id.spDoiTuong);
        /*ArrayList<String> list = new ArrayList<>();
        list.add("School");
        list.add("company");*/
        final String list[] = {"School","Company"};
        int a=1;
        if (a==1){
            String test = "School";
            list[0] = list[1];
            list[1] = test;
        }else {

        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.spinner_list_item,list);
        spinnerDanhSach.setAdapter(adapter);
        spinnerDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(list[i].equals("School")){
                    Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
