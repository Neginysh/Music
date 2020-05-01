package com.example.music.data.requests.api;

import com.example.music.utils.LiveDataCallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.music.utils.Constants.BASE_URL;

//ref: https://medium.com/@amtechnovation/android-architecture-component-mvvm-part-1-a2e7cff07a76
public class RetrofitService {


    private static OkHttpClient client = new OkHttpClient.Builder()

            // establish connection to server
            .connectTimeout(60, TimeUnit.SECONDS)

            // time between each byte read from the server
            .readTimeout(60, TimeUnit.SECONDS)

            // time between each byte sent to server
            .writeTimeout(60, TimeUnit.SECONDS)

            .retryOnConnectionFailure(false)

            .build();


    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


    private static LastfmApi lastfmApi = retrofit.create(LastfmApi.class);

    public static LastfmApi getApi(){
        return lastfmApi;
    }

}