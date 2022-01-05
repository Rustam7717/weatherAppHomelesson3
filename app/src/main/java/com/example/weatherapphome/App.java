package com.example.weatherapphome;

import android.app.Application;
import com.example.weatherapphome.data.remote.RetrofitClient;
import com.example.weatherapphome.data.remote.WeatherAppApi;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static WeatherAppApi weatherAppApi;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient  = new RetrofitClient();
        weatherAppApi = retrofitClient.provideApi();
    }
}
