package com.example.bookingapp.ui.personalpost;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookingapp.EditPostActivity;
import com.example.bookingapp.MainActivity;
import com.example.bookingapp.NewPostActivity;
import com.example.bookingapp.R;
import com.example.bookingapp.adapter.PostAdapter;
import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonalPost extends Fragment {
    private APIService apiService;
    private PersonalPostViewModel mViewModel;
    private PersonalPostViewModel personalPostViewModel;
    private ArrayList<Post> postList;
    private PostAdapter postAdapter;
    private ListView lvPost;
    private FloatingActionButton fabAddPost;
    private Post post;
    private Member member;

    public static PersonalPost newInstance() {
        return new PersonalPost();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_post, container, false);
        init(view);
        addEvents();
        getAllPost();
        return view;
    }

    private void addEvents() {
        lvPost.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                post = postList.get(i);
                return false;
            }
        });


        fabAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewPostActivity.class);
                startActivity(intent);
                reloadPostList();
            }
        });
    }

    private void init(View view) {
        member = loadMemberData();
        apiService = APIUtils.getServer();
        lvPost = view.findViewById(R.id.lvPost);
        fabAddPost = view.findViewById(R.id.fabAddPost);
        postList= new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), R.layout.item_post, postList);
        lvPost.setAdapter(postAdapter);
        registerForContextMenu(lvPost);
    }

    public void getAllPost(){
        apiService.getAll(member.getEmail()).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                switch (response.code()){
                    case 204:
                        break;
                    case 200:
                        if (response.body()!=null){
                            postList.addAll(response.body());
                            postAdapter.notifyDataSetChanged();
                        }else {
                            Log.e("EE", "onResponse: null" );
                        }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.e("PPVM", "onFailure: " + t.getMessage() );
            }
        });
    }

    public void reloadPostList(){
        if (postList != null){
            postList.clear();
            getAllPost();
            postAdapter.notifyDataSetChanged();
        }
    }

    private Member loadMemberData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("memberData", Context.MODE_PRIVATE);
        member = new Member();
        member.setId(sharedPreferences.getInt("id",0));
        member.setName(sharedPreferences.getString("name","default"));
        member.setEmail(sharedPreferences.getString("email","default"));
        member.setPassword(sharedPreferences.getString("password","default"));
        member.setAddress(sharedPreferences.getString("address","default"));
        member.setPhone(sharedPreferences.getString("phone","default"));
        member.setType(sharedPreferences.getInt("type",1));
        return member;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_post, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menuEdit:
                Intent intent = new Intent(getActivity(), EditPostActivity.class);
                intent.putExtra("edit_post", post);
                startActivity(intent);
                reloadPostList();
                break;
            case R.id.menuDelete:
                deleteSelectedPost();
        }
        return super.onContextItemSelected(item);
    }

    private void deleteSelectedPost() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Delete");
        alert.setMessage("Do you want to delete this post?");
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                apiService.delete(post.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        switch (response.code()){
                            case 200:
                                Toast.makeText(getActivity(), "Deleted post successfully", Toast.LENGTH_SHORT).show();
                                reloadPostList();
                                break;
                            case 204:
                                Toast.makeText(getActivity(), "Deleted post failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("AA", "onFailure: " + t.getMessage() );
                    }
                });
            }
        });
        alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

}
