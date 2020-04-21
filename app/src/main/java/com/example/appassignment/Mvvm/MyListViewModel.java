package com.example.appassignment.Mvvm;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.appassignment.model.modelist;
import com.example.appassignment.network.MyApi;
import com.example.appassignment.network.MyClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MyListViewModel extends ViewModel {
    public String artistimage = "";
    public MutableLiveData<modelist> mutableLiveData = new MutableLiveData<modelist>();
    public MutableLiveData<ArrayList> ll = null;
    private ArrayList<modelist> arrayList;
    private ArrayList<modelist> myList;

    public String getImageurl() {
        return artistimage;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        //Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    public MyListViewModel() {
    }


    public MutableLiveData<modelist> getMutableLiveData() {
        arrayList = new ArrayList<>();
        MyApi api = MyClient.getInstance().getMyApi();
        Call<modelist> call = api.getartistdata();
        call.enqueue(new Callback<modelist>() {
            @Override
            public void onResponse(Call<modelist> call, Response<modelist> response) {
                if (response.isSuccessful()) {
                    // your code to get data from the list
                    modelist mm = response.body();
                    myList = new ArrayList<>();
                    mutableLiveData.setValue(mm);
                    for (int i = 0; i < myList.size(); i++) {
                        modelist myk = myList.get(i);
                        arrayList.add(myk);
                   }
                } else {
                }
            }

            @Override
            public void onFailure(Call<modelist> call, Throwable t) {
                t.printStackTrace();
                Log.e("MainActivity " + "test", "  error " + t.toString());

            }
        });
        return mutableLiveData;
    }

}

