package com.example.devicecomp.dto;

import com.example.devicecomp.Model.Laptops;
import com.example.devicecomp.Model.Phones;
import com.example.devicecomp.Model.PortableSpeakers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Products {
     List<Laptops> laptopsList;
     List<Phones> phonesList;
     List<PortableSpeakers> portableSpeakersList;

}
