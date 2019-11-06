package com.example.bookingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookingapp.R;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private Context context;
    private int layout;
    private ArrayList<Post> postList;

    public PostAdapter(Context context, int resource, ArrayList<Post>postList) {
        super(context, resource, postList);
        this.context = context;
        this.layout = resource;
        this.postList = postList;
    }


    @Override
    public int getCount() {
        return postList.size();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = inflater.from(context).inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            viewHolder.txtSkill = convertView.findViewById(R.id.txtSkill);
            viewHolder.txtDescription = convertView.findViewById(R.id.txtDescription);
            viewHolder.txtQuantity = convertView.findViewById(R.id.txtQuantity);
            viewHolder.txtAddress = convertView.findViewById(R.id.txtAddress);
            viewHolder.txtCreateDate = convertView.findViewById(R.id.txtCreatedDate);
            viewHolder.txtAuthor = convertView.findViewById(R.id.txtAuthor);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (postList.size() > 0){
            Post post = postList.get(position);
            viewHolder.txtName.setText("Name: ".concat(post.getName()));
            viewHolder.txtSkill.setText("Skill: ".concat(post.getSkill()));
            viewHolder.txtDescription.setText("Description: ".concat(post.getDescription()));
            viewHolder.txtQuantity.setText("Quantity: ".concat(String.valueOf(post.getQuantity())));
            viewHolder.txtAddress.setText("Address: ".concat(post.getAddress()));
            viewHolder.txtCreateDate.setText("Created date: ".concat(post.getCreatedDate()));
            viewHolder.txtAuthor.setText("Author: ".concat(post.getAuthorId()));
        }

        return convertView;
    }

    public class ViewHolder{
        TextView txtName, txtSkill, txtDescription, txtQuantity, txtAddress, txtCreateDate, txtAuthor;
    }
}