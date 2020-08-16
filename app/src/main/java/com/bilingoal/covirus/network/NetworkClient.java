package com.bilingoal.covirus.network;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private final Retrofit retrofit;

    public NetworkClient(String url) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public NetworkInterface getRequestApi() {
        return retrofit.create(NetworkInterface.class);
    }
}
