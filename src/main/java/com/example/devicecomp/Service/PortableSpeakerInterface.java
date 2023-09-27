package com.example.devicecomp.Service;

import com.example.devicecomp.Model.PortableSpeakers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PortableSpeakerInterface {
    ResponseEntity<String> addSpeakers(PortableSpeakers portableSpeakers);

    ResponseEntity<PortableSpeakers> editSpeakers(int id, PortableSpeakers portableSpeakers);

    ResponseEntity<String> deleteSpeakers(int id);

    ResponseEntity<List<PortableSpeakers>> getAllSpeakers();

    ResponseEntity<List<PortableSpeakers>> recommendSpeakers(String condition, String batteryCapacity);

    ResponseEntity<PortableSpeakers> getById(int id);
}
