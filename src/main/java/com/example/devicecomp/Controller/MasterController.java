package com.example.devicecomp.Controller;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Model.Phones;
import com.example.devicecomp.Model.PortableSpeakers;
import com.example.devicecomp.Service.impl.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("master")
public class MasterController {

    @Autowired
   private MasterService masterService;

    @GetMapping("getAll")
    public ResponseEntity<List<Objects>> gellAllProdcuts(){
        return masterService.getAllProducts();
    }
}
