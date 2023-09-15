package com.example.devicecomp.Service;

import com.example.devicecomp.Model.Phones;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhoneServiceInterface {
    ResponseEntity<String> addPhones(Phones phone);

    ResponseEntity<String> editLaptop(int id, Phones phone);

    ResponseEntity<String> deletePhones(int id);

    ResponseEntity<List<Phones>> getAllPhones();

    ResponseEntity<List<Phones>> recommendPhones(String condition, String price, String storage);
}
