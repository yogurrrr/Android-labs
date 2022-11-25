package com.example.lab3.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lab3.entities.Car;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM Car ORDER BY car_id")
    List<Car> loadAllCars();

    @Insert
    void insertCar(Car car);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);

    @Query("SELECT * FROM Car WHERE car_id = :id")
    Car loadCarById(int id);

    @Query("SELECT * FROM Car WHERE vin_number = :vin")
    List<Car> loadCarsByVin(String vin);

    @Query("SELECT * FROM Car WHERE sale_id = :sale_id")
    List<Car> loadCarsBySaleId(String sale_id);
}
