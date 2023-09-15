package com.example.devicecomp.Controller;

import com.example.devicecomp.Model.Phones;
import com.example.devicecomp.Service.impl.LaptopService;
import com.example.devicecomp.Service.impl.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @PostMapping("all")

    @Operation(tags = {"laptop based functionality"},
            operationId = "addLaptops",
            summary = "adds a laptop",
            description = "takes a body as input and add the object laptop into the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "laptop contents to be added", content = @Content(schema = @Schema(implementation = Phones.class))))
    public ResponseEntity<String> addPhones(@RequestBody Phones phone){
        return phoneService.addPhones(phone);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"laptop based functionality"},
            operationId = "updateLaptops",
            summary = "updates laptop information",
            description = "takes a body as input and edits the object laptop present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a laptop", example = "2"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "edited laptop contents to be added", content = @Content(schema = @Schema(implementation = Phones.class))))
    public ResponseEntity<String> editPhones(@PathVariable int id, @RequestBody Phones phone){
        return phoneService.editLaptop(id,phone);
    }


    @DeleteMapping("delete/{id}")
    @Operation(tags = {"laptop based functionality"},
            operationId = "deleteLaptops",
            summary = "deletes laptop information",
            description = "takes an id as input and deletes the object laptop if present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a laptop", example = "3"))
    public ResponseEntity<String> deletePhones(@PathVariable int id){
        return phoneService.deletePhones(id);
    }


    @GetMapping("getall")
    @Operation(tags = {"laptop based functionality"},
            operationId = "getAllLaptops",
            summary = "prints laptop information",
            description = "retrieves all laptop information from the database")
    public ResponseEntity<List<Phones>> getAllPhones(){
        return phoneService.getAllPhones();
    }

    @GetMapping("getRec")
    @Operation(tags = {"laptop based functionality "},
            operationId = "recommend laptops",
            summary = "provides a list of laptops based on the user entered requirement",
            description = "takes the required condition, price range and specs as input and returns a list of laptops that are suited to the the inputs",
            parameters = {@Parameter(name = "condition", description = "preferred laptop condition", example = "used/brand new"),
                    @Parameter(name = "price", description = "preferred price range(this is a price cap)", example = "1500"),
                    @Parameter(name = "specs", description = "preferred specs", example = "low tier/mid tier/high tier")})
    public ResponseEntity<List<Phones>> recommendPhones(@RequestParam String condition, @RequestParam String price, @RequestParam String storage){
        return phoneService.recommendPhones(condition,price,storage);
    }
}
