package com.example.lab3.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "sale")
public class Sale {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "sale_id")
    String saleId;

    @ColumnInfo(name = "manager_id")
    String managerId;

    @ColumnInfo(name = "cost")
    String cost;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "loan")
    String loan;


    public Sale(String managerId, String cost, String date, String loan) {
        this.managerId = managerId;
        this.cost = cost;
        this.date = date;
        this.loan = loan;
    }

    @Ignore
    public Sale(String saleId, String managerId, String cost, String date, String loan) {
        this.saleId = saleId;
        this.managerId = managerId;
        this.cost = cost;
        this.date = date;
        this.loan = loan;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getLoan() {
        return loan;
    }
}
