package com.example.weatherapphome.data.remote;

import com.example.weatherapphome.data.models.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAppApi {

    @GET("weather")
    Call<WeatherModel> getWeather(
            @Query("q") String city,
            @Query("appid") String appid,
            @Query("units") String units
    );
}
