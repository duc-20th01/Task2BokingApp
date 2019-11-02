package com.example.bookingapp.ui.personalpost;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookingapp.R;
import com.example.bookingapp.adapter.PostAdapter;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;


public class PersonalPost extends Fragment {

    private PersonalPostViewModel mViewModel;
    private PersonalPostViewModel personalPostViewModel;
    private ArrayList<Post> postList;
    private PostAdapter postAdapter;
    private ListView lvPost;
    private FloatingActionButton fabAddPost;
    private Post post;

    public static PersonalPost newInstance() {
        return new PersonalPost();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        personalPostViewModel = ViewModelProviders.of(this).get(PersonalPostViewModel.class);
        View view = inflater.inflate(R.layout.fragment_personal_post, container, false);
        lvPost = view.findViewById(R.id.lvPost);
        fabAddPost = view.findViewById(R.id.fabAddPost);
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(getActivity(), R.layout.item_post, postList);
        lvPost.setAdapter(postAdapter);

        lvPost.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                post = postList.get(i);
                return false;
            }
        });

        final Observer<ArrayList<Post>> postObserver = new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Post> posts) {
                postList.clear();
                postList.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        };

        personalPostViewModel.getPosts().observe(this, postObserver);
        fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //  pass
                //
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });

        // register for context menu
        //    pass
        //

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PersonalPostViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //
        // pass
        //
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //
        // pass
        //
        return super.onContextItemSelected(item);
    }
}
