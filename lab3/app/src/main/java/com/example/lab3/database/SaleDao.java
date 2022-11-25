package com.example.lab3.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lab3.entities.Sale;

import java.util.List;

@Dao
public interface SaleDao {

    @Query("SELECT * FROM Sale ORDER BY sale_id")
    List<Sale> loadAllSales();

    @Insert
    void insertSale(Sale sale);

    @Update
    void updateSale(Sale sale);

    @Delete
    void deleteSale(Sale sale);

    @Query("SELECT * FROM Sale WHERE sale_id = :id")
    Sale loadSaleById(String id);

    @Query("SELECT * FROM Sale WHERE sale_id = :id")
    List<Sale> loadSalesBySaleId(String id);

    @Query("SELECT * FROM Sale WHERE date = :date")
    List<Sale> loadSalesByDate(String date);

    @Query("SELECT * FROM Sale WHERE manager_id = :id ORDER BY manager_id ASC")
    List<Sale> loadSalesByManagerId(String id);

}
