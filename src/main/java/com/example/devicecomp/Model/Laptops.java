package com.example.devicecomp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Laptops {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int storage;
    private String manufacturer;
    private String model;
    private String specs;
    private String price;
    private String UseCondition;
    private String releaseDate;
}
