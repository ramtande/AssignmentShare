package com.example.appassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appassignment.Adapter.MyAdapter;
import com.example.appassignment.Mvvm.MyListViewModel;
import com.example.appassignment.databinding.ActivityMainBinding;
import com.example.appassignment.model.modelist;
import com.example.appassignment.network.NetworkUtil;


public class MainActivity extends AppCompatActivity {
        ActivityMainBinding activityMainBinding;
    SwipeRefreshLayout swipeRefresh;
    private MyListViewModel myListViewModel;
    private RecyclerView recyclerview;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initializationViews();
        myListViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);
               //check the network connectivity when activity is created
        showReclyclelist();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showReclyclelist();
            }

        });


    }

    private void showReclyclelist() {

        if (checkConnection()) {
            getPopularBlog();
        }
        else {
            Toast.makeText(MainActivity.this,R.string.connection_availybility,Toast.LENGTH_LONG).show();;
        }
    }

    private boolean checkConnection() {
        boolean isConnection;
        isConnection = NetworkUtil.getConnectivityStatusString(MainActivity.this);
        return isConnection;

    }

    private void initializationViews() {
        swipeRefresh = activityMainBinding.pulltorefresh;
        recyclerview = activityMainBinding.recyclerview;


    }


    public void getPopularBlog() {
        swipeRefresh.setRefreshing(true);
        myListViewModel.getMutableLiveData().observe(this, new Observer<modelist>() {
            @Override
            public void onChanged(@Nullable modelist blogList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(blogList);
            }
        });
    }

    private void prepareRecyclerView(modelist listItem) {
        adapter = new MyAdapter(listItem.getRows(), MainActivity.this);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }



}
