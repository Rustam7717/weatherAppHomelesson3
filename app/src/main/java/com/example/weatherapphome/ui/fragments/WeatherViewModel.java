package com.example.weatherapphome.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapphome.data.models.WeatherModel;
import com.example.weatherapphome.data.repositories.WeatherRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<WeatherModel> data;
    private WeatherRepository weatherRepository;
    public LiveData<WeatherModel> localLiveData;


    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public MutableLiveData<WeatherModel> getWeatherData(String city) {
        return weatherRepository.getWeather(city);

    }
    public void getAll() {
        localLiveData = weatherRepository.getAll();
    }

}



