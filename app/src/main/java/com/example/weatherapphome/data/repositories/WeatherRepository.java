package com.example.weatherapphome.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapphome.App;
import com.example.weatherapphome.common.Resource;
import com.example.weatherapphome.data.models.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    String apiKey = "f4cd916d91f364d7be8e0a7aa25873f5";
    private String units = "metric";

    public MutableLiveData<Resource<WeatherModel>> getWeather(String city) {
        MutableLiveData<Resource<WeatherModel>> data = new MutableLiveData<>();
        data.setValue(Resource.loading());
        App.weatherAppApi.getWeather(city, apiKey, units).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(response.message(), null));
                }
            }
            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                data.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return data;
    }
}
