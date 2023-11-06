package com.example.smartcontrol.controller;

import com.example.smartcontrol.equipment.AirConditioningDTO;
import com.example.smartcontrol.equipment.AirRepository;
import com.example.smartcontrol.equipment.EquipmentAir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("equipment")
public class EquipController {
    @Autowired
    private AirRepository repository;
    @GetMapping
    public List<AirConditioningDTO> getAll(){
        List<AirConditioningDTO> equips = repository.findAll().stream().map(AirConditioningDTO::new).toList();
        return equips;
    }
}
