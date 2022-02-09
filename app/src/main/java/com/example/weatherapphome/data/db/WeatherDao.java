package com.example.weatherapphome.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherapphome.data.models.WeatherModel;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weathermodel")
    LiveData<WeatherModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(WeatherModel model);

    @Update
    void update(WeatherModel model);

    @Delete
    void delete(WeatherModel model);

    @Query("DELETE FROM weathermodel")
    void deleteAll();
}
