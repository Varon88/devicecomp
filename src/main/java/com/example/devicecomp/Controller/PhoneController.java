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

    @PostMapping("add")
    @Operation(tags = {"phone based functionality"},
            operationId = "addPhone",
            summary = "adds a phone",
            description = "takes a body as input and add the object phone into the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "phone contents to be added", content = @Content(schema = @Schema(implementation = Phones.class))))
    public ResponseEntity<String> addPhones(@RequestBody Phones phone){
        return phoneService.addPhones(phone);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"phone based functionality"},
            operationId = "updatePhone",
            summary = "updates phone information",
            description = "takes a body as input and edits the object phone present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a phone", example = "2"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "edited phone contents to be added", content = @Content(schema = @Schema(implementation = Phones.class))))
    public ResponseEntity<String> editPhones(@PathVariable int id, @RequestBody Phones phone){
        return phoneService.editLaptop(id,phone);
    }


    @DeleteMapping("delete/{id}")
    @Operation(tags = {"phone based functionality"},
            operationId = "deletePhone",
            summary = "deletes phone information",
            description = "takes an id as input and deletes the object phone if present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a phone", example = "3"))
    public ResponseEntity<String> deletePhones(@PathVariable int id){
        return phoneService.deletePhones(id);
    }


    @GetMapping("getall")
    @Operation(tags = {"phone based functionality"},
            operationId = "getAllPhones",
            summary = "prints phone information",
            description = "retrieves all phone information from the database")
    public ResponseEntity<List<Phones>> getAllPhones(){
        return phoneService.getAllPhones();
    }

    @GetMapping("getRec")
    @Operation(tags = {"phone based functionality"},
            operationId = "recommend phones",
            summary = "provides a list of phones based on the user entered requirement",
            description = "takes the required condition, price range and specs as input and returns a list of laptops that are suited to the the inputs",
            parameters = {@Parameter(name = "condition", description = "preferred phone condition", example = "Used/New"),
                    @Parameter(name = "price", description = "preferred price range(this is a price cap)", example = "1500"),
                    @Parameter(name = "storage", description = "preferred storage", example = "64,128,256")})
    public ResponseEntity<List<Phones>> recommendPhones(@RequestParam String condition, @RequestParam int price, @RequestParam String storage){
        return phoneService.recommendPhones(condition,price,storage);
    }
}
