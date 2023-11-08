package com.example.smartcontrol.controller;

import com.example.smartcontrol.equipment.AirRequestDTO;
import com.example.smartcontrol.equipment.AirResponseDTO;
import com.example.smartcontrol.equipment.AirRepository;
import com.example.smartcontrol.equipment.EquipmentAir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("air")
public class EquipController {
    @Autowired
    private AirRepository repository;

    @GetMapping
    public List<AirResponseDTO> getAll(){
        List<AirResponseDTO> equips = repository.findAll().stream().map(AirResponseDTO::new).toList();
        return equips;
    }

    @PostMapping
    public void saveAir(@RequestBody AirRequestDTO data){
        EquipmentAir equipmentAir = new EquipmentAir(data);
        repository.save(equipmentAir);
        return;
    }
}
