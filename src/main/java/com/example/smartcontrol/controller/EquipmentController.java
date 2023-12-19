package com.example.smartcontrol.controller;

import com.example.smartcontrol.domain.equipment.AirRequestDTO;
import com.example.smartcontrol.domain.equipment.AirResponseDTO;
import com.example.smartcontrol.repositories.AirRepository;
import com.example.smartcontrol.domain.equipment.EquipmentAir;
import com.example.smartcontrol.services.SerialCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("air")
public class EquipmentController {
    @Autowired
    private AirRepository repository;
    @Autowired
    private SerialCommunicationService serialService;

    @GetMapping("/test/turnOn")
    public String testTurnOn() {
        serialService.sendMessageToSerialPort("1");
        return "ok";
    }

    @GetMapping("/test/turnOff")
    public String testTurnOff() {
        serialService.sendMessageToSerialPort("0");
        return "ok";
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<AirResponseDTO> equips = repository.findAll().stream().map(AirResponseDTO::new).toList();
        return ResponseEntity.ok(equips);
    }

    @GetMapping("/{division}")
    public ResponseEntity getPorBloco(@PathVariable String division){
        List<EquipmentAir> equips = repository.findByDivision(division);
        List<AirResponseDTO> responseDTOs = equips.stream().map(AirResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{division}/{segment}")
    public ResponseEntity getPorSalaDoBloco(@PathVariable String division, @PathVariable String segment){
        List<EquipmentAir> equips = repository.findByDivisionAndSegment(division, segment);
        List<AirResponseDTO> responseDTOs = equips.stream().map(AirResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PostMapping("/air")
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

    @PutMapping("/updateCondition")
    @Transactional
    public ResponseEntity updateAirAdm(@RequestBody AirRequestDTO data){
        Optional<EquipmentAir> optionalEquipmentAir = repository.findById(data.id());
        if(optionalEquipmentAir.isPresent()){
            EquipmentAir equipmentAir = optionalEquipmentAir.get();

            if(data.condition() == false){
                equipmentAir.setCondition(data.condition());
                equipmentAir.setActive(false);
                return ResponseEntity.ok(equipmentAir);
            } else {
                equipmentAir.setCondition(data.condition());
                equipmentAir.setActive(data.active());
                return ResponseEntity.ok(equipmentAir);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/onoff")
    public ResponseEntity updateOnOFF(@PathVariable String id, @RequestBody Boolean active){
        Optional<EquipmentAir> OptionalEquipmentAir = repository.findById(id);
        if(OptionalEquipmentAir.isPresent()){
            EquipmentAir equipmentAir = OptionalEquipmentAir.get();
            equipmentAir.setActive(active);
            return ResponseEntity.ok(equipmentAir);
        }
        return ResponseEntity.notFound().build();
    }
}
