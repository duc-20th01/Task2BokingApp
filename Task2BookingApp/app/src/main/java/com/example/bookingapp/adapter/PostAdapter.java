package com.example.bookingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookingapp.R;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Post> postList;

    public PostAdapter(Context context, int layout, ArrayList<Post> postList) {
        this.context = context;
        this.layout = layout;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.txtName = view.findViewById(R.id.txtName);
            viewHolder.txtSkill = view.findViewById(R.id.txtSkill);
            viewHolder.txtDescription = view.findViewById(R.id.txtDescription);
            viewHolder.txtQuantity = view.findViewById(R.id.txtQuantity);
            viewHolder.txtAddress = view.findViewById(R.id.txtAddress);
            viewHolder.txtCreateDate = view.findViewById(R.id.txtCreatedDate);
            viewHolder.txtAuthor = view.findViewById(R.id.txtAuthor);
            view.setTag(viewGroup);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (postList.size() > 0){
            Post post = postList.get(i);
            viewHolder.txtName.setText("Name: ".concat(post.getName()));
            viewHolder.txtSkill.setText("Skill: ".concat(post.getSkill()));
            viewHolder.txtDescription.setText("Description: ".concat(post.getDescription()));
            viewHolder.txtQuantity.setText("Quantity: ".concat(String.valueOf(post.getQuantity())));
            viewHolder.txtAddress.setText("Address: ".concat(post.getAddress()));
            viewHolder.txtCreateDate.setText("Created date: ".concat(post.getCreatedDate()));
            viewHolder.txtAuthor.setText("Author: ".concat(post.getAuthorId()));
        }

        return view;
    }
    public class ViewHolder{
        TextView txtName, txtSkill, txtDescription, txtQuantity, txtAddress, txtCreateDate, txtAuthor;
    }
}