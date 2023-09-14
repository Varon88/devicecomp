package com.example.devicecomp.dao;

import com.example.devicecomp.Model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesDao extends JpaRepository<Phones, Integer> {
}
