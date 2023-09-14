package com.example.devicecomp.dao;

import com.example.devicecomp.Model.Laptops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopsDao extends JpaRepository<Laptops,Integer> {

    @Query(value = "SELECT * FROM Laptops l WHERE l.useCondition = :useCondition AND l.price <= :price AND l.specs = :specs ",nativeQuery = true)
    List<Laptops> findAllRec(String useCondition, String price, String specs);
}
