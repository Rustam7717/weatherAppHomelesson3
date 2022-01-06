package com.example.weatherapphome.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.weatherapphome.R;
import com.example.weatherapphome.databinding.FragmentCityNavBinding;


public class CityNavFragment extends Fragment {


    private FragmentCityNavBinding binding;
    private NavController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().
                findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCityNavBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();

    }

    private void initialize() {
        binding.getCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(CityNavFragmentDirections
                        .actionCityNavFragmentToWeatherFragment()
                        .setCityNav(binding.tvCityEnter.getText().toString().trim()));
            }
        });
    }
}
