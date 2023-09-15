package com.example.devicecomp.dao;

import com.example.devicecomp.Model.PortableSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

@Repository
public interface PortableSpeakerDao extends JpaRepository<PortableSpeakers,Integer> {

    @Query(value = "SELECT * FROM PortableSpeakerDao p WHERE p.batteryCapacity = :batteryCapacity AND l.useCondition = :condition", nativeQuery = true)
    HttpStatusCode findAllRec(String condition, String batteryCapacity);
}
