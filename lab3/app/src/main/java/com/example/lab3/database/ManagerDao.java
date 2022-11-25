package com.example.lab3.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lab3.entities.Manager;
import com.example.lab3.entities.Sale;

import java.util.List;
import java.util.Map;

@Dao
public interface ManagerDao {

    @Query("SELECT * FROM manager ORDER BY manager_id")
    List<Manager> loadAllManagers();

    @Insert
    void insertManager(Manager manager);

    @Update
    void updateManager(Manager manager);

    @Delete
    void deleteManager(Manager manager);

    @Query("SELECT * FROM Manager WHERE manager_id = :id")
    Manager loadManagerById(String id);

    @Query("SELECT * FROM Manager WHERE surname = :surname ORDER BY surname ASC")
    List<Manager> loadManagersBySurname(String surname);

    @Query("SELECT * FROM Manager WHERE manager_id = :id ORDER BY surname ASC")
    List<Manager> loadManagersByManagerId(String id);

    @Query("SELECT * FROM Manager WHERE email = :email ORDER BY surname ASC")
    List<Manager> loadManagersByEmail(String email);

    @Query("SELECT sale_id, sale.manager_id, cost, date, loan " +
            "FROM Manager JOIN Sale ON manager.manager_id = sale.manager_id " +
            "WHERE manager.manager_id = :id")
    List<Sale> loadAllManagersAndSales(String id);

}
