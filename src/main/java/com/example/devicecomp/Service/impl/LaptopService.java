package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Service.LaptopServiceInterface;
import com.example.devicecomp.dao.LaptopsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopService implements LaptopServiceInterface {

    @Autowired
    private LaptopsDao laptopsDao;

    @Override
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
    public ResponseEntity<String> editLaptop(int id, Laptops laptops) {
        if(laptopsDao.existsById(id)){
            laptopsDao.deleteById(id);
            laptopsDao.save(new Laptops(id,laptops.getName(),laptops.getStorage(),laptops.getManufacturer(),laptops.getModel(),laptops.getSpecs(),laptops.getPrice(),laptops.getUseCondition(),laptops.getReleaseDate()));
            return new ResponseEntity<>("Update sucessfull", HttpStatus.OK);
        }
        return new ResponseEntity<>("No such item of id found", HttpStatus.BAD_REQUEST);
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
}

















