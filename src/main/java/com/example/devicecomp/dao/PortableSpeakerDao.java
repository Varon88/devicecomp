package com.example.devicecomp.dao;

import com.example.devicecomp.Model.PortableSpeakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortableSpeakerDao extends JpaRepository<PortableSpeakers,Integer> {
}
