package com.example.bookingapp.ui.personalpost;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.bookingapp.model.Post;

import java.util.ArrayList;

public class PersonalPostViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Post>> posts;

    public PersonalPostViewModel(Application application){
        super(application);
        posts = new MutableLiveData<ArrayList<Post>>();
        posts.setValue(getAllPost());
    }

    public MutableLiveData<ArrayList<Post>> getPosts(){
        if (posts == null){
            posts = new MutableLiveData<ArrayList<Post>>();
        }
        return posts;
    }
    public ArrayList getAllPost(){
        ArrayList postList = new ArrayList();
        // connect to api
        return  postList;
    }

}
