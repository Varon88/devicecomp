package com.example.devicecomp.Controller;


import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Model.PortableSpeakers;
import com.example.devicecomp.Service.impl.PortableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("speaker")
public class PortableSpeakerController {

    @Autowired
    private PortableService portableService;

    @PostMapping("add")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "addSpeaker",
            summary = "adds a speaker",
            description = "takes a body as input and add the object Speaker into the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Speaker contents to be added", content = @Content(schema = @Schema(implementation = PortableSpeakers.class))))
    public ResponseEntity<String> addSpeakers(@RequestBody PortableSpeakers portableSpeakers){
        return portableService.addSpeakers(portableSpeakers);
    }

    @PutMapping("update/{id}")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "updateSpeakers",
            summary = "updates Speaker information",
            description = "takes a body as input and edits the object Speaker present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a Speaker", example = "2"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "edited Speaker contents to be added", content = @Content(schema = @Schema(implementation = PortableSpeakers.class))))
    public ResponseEntity<PortableSpeakers> editSpeaker(@PathVariable int id, @RequestBody PortableSpeakers portableSpeakers){
        return portableService.editSpeakers(id,portableSpeakers);
    }


    @DeleteMapping("delete/{id}")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "deleteSpeakers",
            summary = "deletes Speaker information",
            description = "takes an id as input and deletes the object Speaker if present in the database",
            parameters = @Parameter(name = "id", description = "id referencing a Speaker", example = "3"))
    public ResponseEntity<String> deleteSpeakers(@PathVariable int id){
        return portableService.deleteSpeakers(id);
    }


    @GetMapping("getall")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "getAllSpeakers",
            summary = "prints Speaker information",
            description = "retrieves all Speaker information from the database")
    public ResponseEntity<List<PortableSpeakers>> getAllSpeakers(){
        return portableService.getAllSpeakers();
    }

    @GetMapping("getRec")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "recommend Speakers",
            summary = "provides a list of Speakers based on the user entered requirement",
            description = "takes the required condition and battery capacity as input and returns a list of Speakers that are suited to the the inputs",
            parameters = {@Parameter(name = "condition", description = "preferred Speaker condition", example = "Used/New"),
                    @Parameter(name = "batteryCapacity", description = "refers to the battery capacity of the speaker in mAh", example = "1500")})
    public ResponseEntity<List<PortableSpeakers>> recommendSpeakers(@RequestParam String condition, @RequestParam String batteryCapacity){
        return portableService.recommendSpeakers(condition,batteryCapacity);
    }

    @GetMapping("getById/{id}")
    @Operation(tags = {"Portable speaker based functionality"},
            operationId = "get speakers by id",
            summary = "gets the speaker by the id passed",
            description = "takes a path variable and returns and object of speaker based on whether the speaker is present or not",
            parameters = @Parameter(name = "id", description = "id referencing a speaker", example = "3"))
    public ResponseEntity<PortableSpeakers> getById(@PathVariable int id){
        return portableService.getById(id);
    }
}

