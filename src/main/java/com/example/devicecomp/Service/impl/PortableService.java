package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.PortableSpeakers;
import com.example.devicecomp.Service.PortableSpeakerInterface;
import com.example.devicecomp.dao.PortableSpeakerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PortableService implements PortableSpeakerInterface {

    @Autowired
    private PortableSpeakerDao portableSpeakerDao;


    @Override
    @CacheEvict(value = "getAllSpeaker", allEntries = true)
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
    @CacheEvict(value = "getAllSpeaker", allEntries = true)
    public ResponseEntity<PortableSpeakers> editSpeakers(int id, PortableSpeakers portableSpeakers) {
        if(portableSpeakerDao.existsById(id)){
            Optional<PortableSpeakers> optionalPortableSpeakers = portableSpeakerDao.findById(id);
            if(optionalPortableSpeakers.isPresent()){
                PortableSpeakers previousSpeaker = optionalPortableSpeakers.get();
                previousSpeaker.setName(portableSpeakers.getName());
                previousSpeaker.setManufacturer(portableSpeakers.getManufacturer());
                previousSpeaker.setModel(portableSpeakers.getModel());
                previousSpeaker.setBatteryCapacity(portableSpeakers.getBatteryCapacity());
                previousSpeaker.setUseCondition(portableSpeakers.getUseCondition());
                previousSpeaker.setReleaseDate(portableSpeakers.getReleaseDate());
                return new ResponseEntity<>(portableSpeakerDao.save(previousSpeaker), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new PortableSpeakers(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @CacheEvict(value = "getAllSpeaker", allEntries = true)

    public ResponseEntity<String> deleteSpeakers(int id) {
        if(portableSpeakerDao.existsById(id)){
            portableSpeakerDao.deleteById(id);
            return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Cacheable(cacheNames = "getAllSpeaker")
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
            return new ResponseEntity<>(portableSpeakerDao.findAllRec(condition,batteryCapacity), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<PortableSpeakers> getById(int id) {
        if(portableSpeakerDao.existsById(id)){
            return new ResponseEntity<>(portableSpeakerDao.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new PortableSpeakers(), HttpStatus.BAD_REQUEST);
    }
}
