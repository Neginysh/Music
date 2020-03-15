package com.example.music.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//ref: https://medium.com/@amtechnovation/android-architecture-component-mvvm-part-1-a2e7cff07a76
public class RetrofitService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=cher&api_key=7da8db4082844f11d8d41b516c75ffa3&format=json")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}