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
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookingapp.R;

import java.util.ArrayList;

public class EditProfile extends Fragment {

    private EditProfileViewModel mViewModel;
    private Spinner spinnerDanhSach;
    private RadioButton btTruong,btCongTy;

    public static EditProfile newInstance() {
        return new EditProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        btTruong = (RadioButton) root.findViewById(R.id.rbTruong);
        btCongTy = (RadioButton) root.findViewById(R.id.rbCongTy);
        btTruong.setChecked(true);
        if(btTruong.isChecked()==true){
            Toast.makeText(getActivity(), "Truong", Toast.LENGTH_SHORT).show();
            btTruong.setChecked(false);
            btCongTy.setChecked(true);
        }
        else {
            Toast.makeText(getActivity(), "Deo phai r", Toast.LENGTH_SHORT).show();
        }

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
