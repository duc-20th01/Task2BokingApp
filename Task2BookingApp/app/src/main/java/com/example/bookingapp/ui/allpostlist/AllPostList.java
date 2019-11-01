package com.example.bookingapp.ui.allpostlist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookingapp.R;
import com.example.bookingapp.adapter.PostAdapter;
import com.example.bookingapp.api.APIService;
import com.example.bookingapp.api.APIUtils;
import com.example.bookingapp.models.Post;
import com.example.bookingapp.util.ProgressDialogF;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPostList extends Fragment {
    private AllPostListViewModel mViewModel;
    private APIService apiService;
    private ArrayList<Post> data;
    private ListView lvPost;
    private PostAdapter adapter;
    private Post post;

    public static AllPostList newInstance() {
        return new AllPostList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_all_post_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllPostListViewModel.class);
        // TODO: Use the ViewModel
        apiService = APIUtils.getServer();

        getListContents();

        lvPost = getView().findViewById(R.id.lv_allpostlist);
        data = new ArrayList<>();
        adapter = new PostAdapter(getActivity(), R.layout.lv_post, data);
        lvPost.setAdapter(adapter);

        // Event
        lvPost.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                post = data.get(i);
                /*return true means: that the event has been handled. No events will be fired after this point.
                return false means: the event has NOT been handled. Any other events to do with this click will still fire.
                */
                return false;
            }
        });
    }

    private void getListContents() {
        ProgressDialogF.showLoading(getActivity());
        apiService.getAll().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                ProgressDialogF.hideLoading();

                if (response.code() == 204) {
                    Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
                }

                // Success
                if (response.code() == 200) {
                    // Refresh data
                    data.clear();
                    data.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                ProgressDialogF.hideLoading();
                Toast.makeText(getActivity(), "An error occurred!", Toast.LENGTH_SHORT).show();
                Log.d("getListContents", "onFailure : " + t.toString());
            }
        });
    }
}
