package com.example.lab3.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "manager")
public class Manager {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "manager_id")
    String managerId;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "surname")
    String surname;

    @ColumnInfo(name = "phone")
    String phone;

    @ColumnInfo(name = "email")
    String email;


    public Manager(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    @Ignore
    public Manager(String managerId, String name, String surname, String phone, String email) {
        this.managerId = managerId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
