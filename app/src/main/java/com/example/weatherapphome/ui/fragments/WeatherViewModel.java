package com.example.weatherapphome.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapphome.common.Resource;
import com.example.weatherapphome.data.models.WeatherModel;
import com.example.weatherapphome.data.repositories.WeatherRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private WeatherRepository repository;
    public LiveData<Resource<WeatherModel>> liveData;
    public LiveData<WeatherModel> localLiveData;

    @Inject
    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public void getWeatherData(String cityName) {
        liveData = repository.getWeather(cityName);
    }

    public void getAll() {
        localLiveData = repository.getAll();
    }
}



