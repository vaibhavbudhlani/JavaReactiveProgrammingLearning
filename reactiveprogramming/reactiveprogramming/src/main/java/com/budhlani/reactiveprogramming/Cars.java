package com.budhlani.reactiveprogramming;

import lombok.Data;

import java.util.List;
@Data
public class Cars {
    public Cars(String name, int id, String effectiveDate, String expiryDate,STATUS status) {
        this.name = name;
        this.id = id;
        this.effectiveDate = effectiveDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    String name;
    int id;
    List<String> models;
    String effectiveDate;
    String expiryDate;
    private STATUS status;
    public enum STATUS {
       Booked,
        NotBooked
    }

    public Cars(){

    }


}
