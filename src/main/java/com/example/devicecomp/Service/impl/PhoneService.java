package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Model.Phones;
import com.example.devicecomp.Service.PhoneServiceInterface;
import com.example.devicecomp.dao.PhonesDao;
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
public class PhoneService implements PhoneServiceInterface {

    @Autowired
    private PhonesDao phonesDao;

    @Override
    @CacheEvict(value="getAllPhone", allEntries = true)
    public ResponseEntity<String> addPhones(Phones phone) {
        try{
            phonesDao.save(phone);
            return new ResponseEntity<>("Addition successful", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Addition unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @CacheEvict(value="getAllPhone", allEntries = true)
    public ResponseEntity<Phones> editLaptop(int id, Phones phone) {
        if(phonesDao.existsById(id)){
            Optional<Phones> optionalPhone = phonesDao.findById(id);
            if(optionalPhone.isPresent()){
                Phones previousPhone = optionalPhone.get();
                previousPhone.setName(phone.getName());
                previousPhone.setStorage(phone.getStorage());
                previousPhone.setManufacturer(phone.getManufacturer());
                previousPhone.setModel(phone.getModel());
                previousPhone.setBatteryCapacity(phone.getBatteryCapacity());
                previousPhone.setPrice(phone.getPrice());
                previousPhone.setUseCondition(phone.getUseCondition());
                previousPhone.setReleaseDate(phone.getReleaseDate());
                return new ResponseEntity<>(phonesDao.save(previousPhone), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Phones(), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value="getAllPhone", allEntries = true)
    public ResponseEntity<String> deletePhones(int id) {
        try {
            phonesDao.deleteById(id);
            return new ResponseEntity<>("Deletion successful", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Cacheable(cacheNames = "getAllPhone")
    public ResponseEntity<List<Phones>> getAllPhones() {
        try{
            return new ResponseEntity<>(phonesDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Phones>> recommendPhones(String condition, int price, String storage) {
        try{
            return new ResponseEntity<>(phonesDao.findAllRec(condition,price,storage), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Phones> getById(int id) {
        if(phonesDao.existsById(id)){
            return new ResponseEntity<>(phonesDao.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Phones(), HttpStatus.BAD_REQUEST);
    }
}

