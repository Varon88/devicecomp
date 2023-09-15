package com.example.devicecomp.Service.impl;

import com.example.devicecomp.Model.Phones;
import com.example.devicecomp.Service.PhoneServiceInterface;
import com.example.devicecomp.dao.PhonesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService implements PhoneServiceInterface {

    @Autowired
    private PhonesDao phonesDao;

    @Override
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
    public ResponseEntity<String> editLaptop(int id, Phones phone) {
        if(phonesDao.existsById(id)){
            phonesDao.deleteById(id);
            phonesDao.save(new Phones(id , phone.getName(),phone.getStorage(),phone.getManufacturer(),phone.getModel(),phone.getBatteryCapacity(),phone.getPrice(),phone.getUseCondition(),phone.getReleaseDate()));
            return new ResponseEntity<>("Edit successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Edit unsuccessful; item not present", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deletePhones(int id) {
        try {
            if (phonesDao.existsById(id)) {
                phonesDao.deleteById(id);
                return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Deletion unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Phones>> getAllPhones() {
        try{
            return new ResponseEntity<>(phonesDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Phones>> recommendPhones(String condition, String price, String storage) {
        try{
            return new ResponseEntity<>(phonesDao.findAllRec(condition,price,storage), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
