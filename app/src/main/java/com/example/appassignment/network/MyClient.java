package com.example.appassignment.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static final String BASE_URL="https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    private static MyClient myClient;



    private Retrofit retrofit;
    private MyClient(){
       // retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

      /*  Retrofit*/ retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createDefaultOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()/*rxAdapter*/)
                .build();
    }
    private OkHttpClient createDefaultOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();
    }
    public static synchronized MyClient getInstance(){
        if (myClient==null){
            myClient=new MyClient();
        }
        return myClient;
    }
    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }


}
