package com.example.devicecomp.Controller;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Service.impl.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("laptop")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping("all")
    @Operation(tags = {"laptop based functionality"},
                operationId = "addLaptops",
                summary = "adds a laptop",
                description = "takes a body as input and add the object laptop into the database",
                requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "laptop contents to be added", content = @Content(schema = @Schema(implementation = Laptops.class))))
    public ResponseEntity<String> addLaptops(@RequestBody Laptops laptop){
        return laptopService.addLaptop(laptop);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"laptop based functionality"},
            operationId = "updateLaptops",
            summary = "updates laptop information",
            description = "takes a body as input and edits the object laptop present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a laptop", example = "2"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "edited laptop contents to be added", content = @Content(schema = @Schema(implementation = Laptops.class))))
    public ResponseEntity<String> editLaptops(@PathVariable int id, @RequestBody Laptops laptops){
        return laptopService.editLaptop(id,laptops);
    }


    @DeleteMapping("delete/{id}")
    @Operation(tags = {"laptop based functionality"},
            operationId = "deleteLaptops",
            summary = "deletes laptop information",
            description = "takes an id as input and deletes the object laptop if present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a laptop", example = "3"))
    public ResponseEntity<String> deleteLaptops(@PathVariable int id){
        return laptopService.deleteLaptop(id);
    }


    @GetMapping("getall")
    @Operation(tags = {"laptop based functionality"},
            operationId = "getAllLaptops",
            summary = "prints laptop information",
            description = "retrieves all laptop information from the database")
    public ResponseEntity<List<Laptops>> getAllLaptops(){
        return laptopService.getAllLaptops();
    }

    @GetMapping("getRec")
    @Operation(tags = {"laptop based functionality"},
                operationId = "deleteLaptops",
                summary = "deletes laptop information",
                description = "takes an id as input and deletes the object laptop if present in the database",
                parameters = {@Parameter(name = "condition", description = "preferred laptop condition", example = "used/brand new"),
                             @Parameter(name = "price", description = "preferred price range(this is a price cap)", example = "1500"),
                             @Parameter(name = "specs", description = "preferred specs", example = "low tier/mid tier/high tier")})
    public ResponseEntity<List<Laptops>> recommendLaptops(@RequestParam String condition, @RequestParam String price, @RequestParam String specs){
        return laptopService.recommendLaptops(condition,price,specs);
    }

}
