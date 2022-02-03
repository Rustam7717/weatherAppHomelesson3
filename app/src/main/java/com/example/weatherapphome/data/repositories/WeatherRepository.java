package com.example.weatherapphome.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.weatherapphome.data.db.dao.WeatherDao;
import com.example.weatherapphome.data.models.WeatherModel;
import com.example.weatherapphome.data.remote.WeatherAppApi;
import com.example.weatherapphome.ui.fragments.WeatherFragment;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherAppApi weatherAppApi;
    private WeatherDao weatherDao;
    private WeatherFragment weatherFragment = new WeatherFragment();

    @Inject
    public WeatherRepository(WeatherAppApi weatherAppApi, WeatherDao dao) {
        this.weatherAppApi = weatherAppApi;
        this.weatherDao = weatherDao;
    }

    String apiKey = "f4cd916d91f364d7be8e0a7aa25873f5";
    private String units = "metric";

    public MutableLiveData<WeatherModel> getWeather(String city) {
        MutableLiveData<WeatherModel> data = new MutableLiveData<>();
        weatherAppApi.getWeather(city, apiKey, units).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }
        });
        return data;
    }


    public LiveData<WeatherModel> getAll() {
        return weatherDao.getAll();

    }
}
