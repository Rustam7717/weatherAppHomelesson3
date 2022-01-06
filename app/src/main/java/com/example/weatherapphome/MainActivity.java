package com.example.weatherapphome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.example.weatherapphome.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setNavigation();


    }

    private void setNavigation() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host);
        NavController navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.weatherFragment).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }
}
