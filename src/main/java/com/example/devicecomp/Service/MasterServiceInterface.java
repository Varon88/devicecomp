package com.example.devicecomp.Service;

import com.example.devicecomp.dto.Products;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface MasterServiceInterface {
    ResponseEntity<Products> getAllProducts();
}
