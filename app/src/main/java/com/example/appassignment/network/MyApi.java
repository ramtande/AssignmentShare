package com.example.appassignment.network;

import com.example.appassignment.model.modelist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("facts.json")
    Call<modelist> getartistdata();
   /* Call<List<modelist>> getartistdata();*/
}
