package com.example.smartcontrol.controller;

import com.example.smartcontrol.equipment.AirRequestDTO;
import com.example.smartcontrol.equipment.AirResponseDTO;
import com.example.smartcontrol.equipment.AirRepository;
import com.example.smartcontrol.equipment.EquipmentAir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("air")
public class EquipController {
    @Autowired
    private AirRepository repository;

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping
    public List<AirResponseDTO> getAll(){
        List<AirResponseDTO> equips = repository.findAll().stream().map(AirResponseDTO::new).toList();
        return equips;
    }

    @GetMapping("/{division}")
    public List<AirResponseDTO> getPorBloco(@PathVariable String division){
        List<EquipmentAir> equips = repository.findByDivision(division);
        List<AirResponseDTO> responseDTOs = equips.stream().map(AirResponseDTO::new).collect(Collectors.toList());
        return responseDTOs;
    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping
    public ResponseEntity saveAir(@RequestBody AirRequestDTO data){
        EquipmentAir equipmentAir = new EquipmentAir(data);
        repository.save(equipmentAir);
        return ResponseEntity.ok(equipmentAir);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAir(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateAir(@RequestBody AirRequestDTO data){
        Optional<EquipmentAir> optionalEquipmentAir = repository.findById(data.id());
        if(optionalEquipmentAir.isPresent()){
            EquipmentAir equipmentAir = optionalEquipmentAir.get();
            equipmentAir.setCondition(data.condition());
            equipmentAir.setActive(data.active());
            return ResponseEntity.ok(equipmentAir);
        }
        return ResponseEntity.notFound().build();
    }
}
