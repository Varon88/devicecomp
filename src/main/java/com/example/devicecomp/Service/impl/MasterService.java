package com.example.devicecomp.Service.impl;


import com.example.devicecomp.Service.MasterServiceInterface;
import com.example.devicecomp.dao.LaptopsDao;
import com.example.devicecomp.dao.PhonesDao;
import com.example.devicecomp.dao.PortableSpeakerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MasterService implements MasterServiceInterface {

    @Autowired
    private LaptopsDao laptopsDao;
    @Autowired
    private PhonesDao phonesDao;
    @Autowired
    private PortableSpeakerDao portableSpeakerDao;

    List allItems = new ArrayList<>();

    @Override
    @Cacheable(cacheNames = "getAll")
    public ResponseEntity<List<Objects>> getAllProducts() {
        allItems.add(laptopsDao.findAll());
        allItems.add(phonesDao.findAll());
        allItems.add(portableSpeakerDao.findAll());
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }
}
