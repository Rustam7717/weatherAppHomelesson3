package com.example.weatherapphome.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapphome.databinding.FragmentWeatherBinding;


public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setObservers();
    }


    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    private void setObservers() {
        viewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherModelsResource ->
                binding.nameApp.setText(weatherModelsResource.data.getName()));
    }
}