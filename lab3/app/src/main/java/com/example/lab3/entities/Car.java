package com.example.lab3.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "car")
public class Car {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "car_id")
    int carId;

    @ColumnInfo(name = "brand")
    String brand;

    @ColumnInfo(name = "model")
    String model;

    @ColumnInfo(name = "sale_id")
    String saleId;

    @ColumnInfo(name = "vin_number")
    String vinNumber;

    @ColumnInfo(name = "mileage")
    String mileage;


    public Car(String brand, String model, String saleId, String vinNumber, String mileage) {
        this.brand = brand;
        this.model = model;
        this.saleId = saleId;
        this.vinNumber = vinNumber;
        this.mileage = mileage;
    }

    @Ignore
    public Car(int id, String brand, String model, String saleId, String vinNumber, String mileage) {
        this.carId = id;
        this.brand = brand;
        this.model = model;
        this.saleId = saleId;
        this.vinNumber = vinNumber;
        this.mileage = mileage;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
