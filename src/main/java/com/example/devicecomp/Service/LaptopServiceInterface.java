package com.example.devicecomp.Service;

import com.example.devicecomp.Model.Laptops;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LaptopServiceInterface {
    ResponseEntity<String> addLaptop(Laptops laptop);

    ResponseEntity<String> editLaptop(int id, Laptops laptops);

    ResponseEntity<String> deleteLaptop(int id);

    ResponseEntity<List<Laptops>> getAllLaptops();

    ResponseEntity<List<Laptops>> recommendLaptops(String condition, int price, String specs);
}
