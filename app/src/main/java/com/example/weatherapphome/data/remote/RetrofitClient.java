package com.example.weatherapphome.data.remote;

import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient()).build();

    private OkHttpClient provideClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(provideLoggingInterceptor())
                .build();
    }

    private Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public WeatherAppApi provideApi() {
        return retrofit.create(WeatherAppApi.class);
    }
}
