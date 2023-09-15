package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.PortableSpeakers;
import com.example.devicecomp.Service.PortableSpeakerInterface;
import com.example.devicecomp.dao.PortableSpeakerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortableService implements PortableSpeakerInterface {

    @Autowired
    private PortableSpeakerDao portableSpeakerDao;


    @Override
    public ResponseEntity<String> addSpeakers(PortableSpeakers portableSpeakers) {
        try{
            portableSpeakerDao.save(portableSpeakers);
            return new ResponseEntity<>("Addition successful", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Addition unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> editSpeakers(int id, PortableSpeakers portableSpeakers) {
        if(portableSpeakerDao.existsById(id)){
            portableSpeakerDao.deleteById(id);
            portableSpeakerDao.save(new PortableSpeakers(id, portableSpeakers.getName(), portableSpeakers.getManufacturer(),portableSpeakers.getModel(),portableSpeakers.getBatteryCapacity(),portableSpeakers.getUseCondition(),portableSpeakers.getReleaseDate()));
            return new ResponseEntity<>("Edit successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Edit unsuccessful; item not present", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteSpeakers(int id) {
        if(portableSpeakerDao.existsById(id)){
            portableSpeakerDao.deleteById(id);
            return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Cacheable("getAll")
    public ResponseEntity<List<PortableSpeakers>> getAllSpeakers() {
        try{
            return new ResponseEntity<>(portableSpeakerDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<PortableSpeakers>> recommendSpeakers(String condition, String batteryCapacity) {
        try {
            return new ResponseEntity<>(portableSpeakerDao.findAllRec(condition,batteryCapacity));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
