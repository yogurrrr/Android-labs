package com.example.lab3.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lab3.entities.Manager;

import java.util.List;

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
}
