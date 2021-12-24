package com.example.weatherapphome.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.weatherapphome.common.Resource;
import com.example.weatherapphome.data.models.WeatherModel;
import com.example.weatherapphome.data.repositories.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private final String city = "Bishkek";
    public final WeatherRepository weatherRepository = new WeatherRepository();
    public LiveData<Resource<WeatherModel>> data;

    public LiveData<Resource<WeatherModel>> getWeatherData(){
        return weatherRepository.getWeather(city);

    }
}
