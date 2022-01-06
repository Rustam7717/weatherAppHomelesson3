package com.example.weatherapphome.ui.fragments;

import static java.lang.String.valueOf;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.weatherapphome.R;
import com.example.weatherapphome.databinding.FragmentWeatherBinding;
import java.time.Instant;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private NavController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(requireView());
        initialize();
        init();
        setObservers();
    }

    private void initialize() {
        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.action_weatherFragment_to_cityNavFragment);
            }
        });
    }


    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setObservers() {
        viewModel.getWeatherData(WeatherFragmentArgs.fromBundle(getArguments())
                .getCityNav())
                .observe(getViewLifecycleOwner(), weatherModels -> {
            binding.location.setText(weatherModels.getName());
            binding.weatherDate.setText(valueOf(Instant.ofEpochSecond(weatherModels.getDt())));
            binding.dayTime.setText(valueOf(weatherModels.getTimezone()));
            binding.weatherTemp.setText(valueOf(weatherModels.getMain().getFeelsLike()));
            binding.tempDown.setText(valueOf(weatherModels.getMain().getTempMin()));
            binding.tempUp.setText(valueOf(weatherModels.getMain().getTempMax()));
            binding.wind.setText(valueOf(weatherModels.getWind().getSpeed()));
            binding.humidity.setText(valueOf(weatherModels.getMain().getHumidity()));
            binding.pressure.setText(valueOf(weatherModels.getMain().getPressure()));
            binding.sunrise.setText(valueOf(weatherModels.getSys().getSunrise()));
            binding.sunset.setText(valueOf(weatherModels.getSys().getSunset()));

        });
    }
}

