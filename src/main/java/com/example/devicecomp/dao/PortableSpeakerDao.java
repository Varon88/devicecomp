package com.example.devicecomp.dao;

import com.example.devicecomp.Model.PortableSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortableSpeakerDao extends JpaRepository<PortableSpeakers,Integer> {

    @Query(value = "SELECT * FROM portable_speakers p WHERE p.battery_capacity <= :batteryCapacity AND p.use_condition = :condition", nativeQuery = true)
    List<PortableSpeakers> findAllRec(String condition, String batteryCapacity);
}
