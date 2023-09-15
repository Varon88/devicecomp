package com.example.devicecomp.dao;

import com.example.devicecomp.Model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonesDao extends JpaRepository<Phones, Integer> {

    @Query(value = "SELECT * FROM Phones p WHERE p.useCondition = :condition AND l.price <= :price AND l.storage = :specs", nativeQuery = true)
    List<Phones> findAllRec(String condition, String price, String storage);
}
