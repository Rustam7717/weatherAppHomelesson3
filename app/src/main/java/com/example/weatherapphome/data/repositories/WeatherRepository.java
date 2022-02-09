package com.example.weatherapphome.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapphome.common.Resource;
import com.example.weatherapphome.data.db.WeatherDao;
import com.example.weatherapphome.data.models.WeatherModel;
import com.example.weatherapphome.data.remote.WeatherAppApi;
import com.example.weatherapphome.ui.fragments.WeatherFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private WeatherAppApi api;
    private WeatherDao weatherDao;
    private WeatherFragment weatherFragment = new WeatherFragment();

    @Inject
    public WeatherRepository(WeatherAppApi api, WeatherDao weatherDao) {
        this.api = api;
        this.weatherDao = weatherDao;
    }

    public MutableLiveData<Resource<WeatherModel>> getWeather(String cityName) {
        MutableLiveData<Resource<WeatherModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Resource.loading());
        api.getWeather(cityName, "34f284ef687268abb84bca32a3522cf7", "metric")
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            weatherDao.deleteAll();
                            mutableLiveData.setValue(Resource.success(response.body()));
                            weatherDao.insertAll(response.body());
                            weatherDao.update(response.body());
                        } else {
                            mutableLiveData.setValue(Resource.error(null, response.message()));
                            weatherDao.delete(response.body());
                        }

                    }


                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {
                        mutableLiveData.setValue(Resource.error(null, t.getLocalizedMessage()));
                    }
                });
        return mutableLiveData;
    }

    public LiveData<WeatherModel> getAll() {
        return weatherDao.getAll();
    }
}
