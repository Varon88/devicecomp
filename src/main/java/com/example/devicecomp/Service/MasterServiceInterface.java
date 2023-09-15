package com.example.devicecomp.Service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface MasterServiceInterface {
    ResponseEntity<List<Objects>> getAllProducts();
}
