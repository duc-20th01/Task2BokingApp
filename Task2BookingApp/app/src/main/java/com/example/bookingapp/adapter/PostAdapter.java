package com.example.bookingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bookingapp.R;
import com.example.bookingapp.models.Post;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    Context context;
    ArrayList<Post> arrayList;
    int layoutResource;

    public PostAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource, null);
        TextView txt1 = convertView.findViewById(R.id.tv_Name);
        txt1.setText(arrayList.get(position).getName());
        TextView txt2 = convertView.findViewById(R.id.tv_skill);
        txt2.setText(arrayList.get(position).getSkill());
        TextView txt3 = convertView.findViewById(R.id.tv_desc);
        txt2.setText(arrayList.get(position).getDescreption());
        TextView txt4 = convertView.findViewById(R.id.tv_quant);
        txt2.setText(arrayList.get(position).getQuantity());
        TextView txt5 = convertView.findViewById(R.id.tv_address);
        txt2.setText(arrayList.get(position).getAddress());
        TextView txt6 = convertView.findViewById(R.id.tv_date);
        txt2.setText(arrayList.get(position).getCreatedDate());
        TextView txt7 = convertView.findViewById(R.id.tv_email);
        txt2.setText(arrayList.get(position).getEmail());
        return convertView;
    }
}
