package com.example.bookingapp.ui.personalpost;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.model.Member;
import com.example.bookingapp.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalPostViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ArrayList<Post> postList;
    private Member member;
    private APIService apiService;

    public PersonalPostViewModel(Application application){
        super(application);
        member = loadMemberData(application);
        apiService = APIUtils.getServer();
        postList = new ArrayList<>();
    }

    private Member loadMemberData(Application application){
        SharedPreferences sharedPreferences = application.getSharedPreferences("memberData", Context.MODE_PRIVATE);
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

    public ArrayList<Post> getAllPost(){
        apiService.getAll(member.getEmail()).enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                switch (response.code()){
                    case 204:
                        Log.e("EE", "onResponse: 204" );
                        break;

                    case 200:
                        Log.e("EE", "onResponse: 200" );
                        if (response.body()!=null){
                            Log.e("EE", "onResponse: " + response.body().toString() );
                            postList.clear();
                            postList.addAll(response.body());
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
        Log.e("EE", "getAllPost: " + postList.size() );
        return  postList;
    }

}
