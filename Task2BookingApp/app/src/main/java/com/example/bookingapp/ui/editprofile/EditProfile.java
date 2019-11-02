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
import android.widget.Spinner;

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
        /*spinnerDanhSach = (Spinner) root.findViewById(R.id.spDoiTuong);
        ArrayList<String> list =new ArrayList<>();
        list.add("Công Ty");
        list.add("Nhà trường");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner_list_item,list);
        spinnerDanhSach.setAdapter(adapter);*/
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
