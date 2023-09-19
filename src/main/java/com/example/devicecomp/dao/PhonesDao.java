package com.example.devicecomp.dao;

import com.example.devicecomp.Model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonesDao extends JpaRepository<Phones, Integer> {

    @Query(value = "SELECT * FROM Phones p1 WHERE p1.use_condition = :condition AND p1.price <= :price AND p1.storage = :storage", nativeQuery = true)
    List<Phones> findAllRec(String condition, int price, String storage);
}
