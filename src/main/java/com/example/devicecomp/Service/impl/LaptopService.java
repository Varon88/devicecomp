package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Service.LaptopServiceInterface;
import com.example.devicecomp.dao.LaptopsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopService implements LaptopServiceInterface {

    @Autowired
    private LaptopsDao laptopsDao;

    @Override
    @CacheEvict(value = "getAllLaptop", allEntries = true)
    public ResponseEntity<String> addLaptop(Laptops laptop) {
        try{
            laptopsDao.save(laptop);
            return new ResponseEntity<>("Addition successful", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Addition unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @CacheEvict(value = "getAllLaptop", allEntries = true)
    public ResponseEntity<Laptops> editLaptop(int id, Laptops laptops) {
        if(laptopsDao.existsById(id)){
            Optional<Laptops> optionalPreviousLaptop = laptopsDao.findById(id);
            if (optionalPreviousLaptop.isPresent()) {
                Laptops previousLaptop = optionalPreviousLaptop.get();
                previousLaptop.setName(laptops.getName());
                previousLaptop.setStorage(laptops.getStorage());
                previousLaptop.setManufacturer(laptops.getManufacturer());
                previousLaptop.setModel(laptops.getModel());
                previousLaptop.setSpecs(laptops.getSpecs());
                previousLaptop.setPrice(laptops.getPrice());
                previousLaptop.setUseCondition(laptops.getUseCondition());
                previousLaptop.setReleaseDate(laptops.getReleaseDate());

                return new ResponseEntity<>(laptopsDao.save(previousLaptop), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Laptops(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteLaptop(int id) {
       try{
           laptopsDao.deleteById(id);
           return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
       }catch(Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    @Cacheable(cacheNames = "getAllLaptop")
    public ResponseEntity<List<Laptops>> getAllLaptops() {
        return new ResponseEntity<>(laptopsDao.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Laptops>> recommendLaptops(String useCondition, int price, String specs) {
        try{
            return new ResponseEntity<>(laptopsDao.findAllRec(useCondition,price,specs), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Laptops> getById(int id) {
        if(laptopsDao.findById(id).isPresent()){
            return new ResponseEntity<>(laptopsDao.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Laptops(),HttpStatus.OK);
    }
}

















