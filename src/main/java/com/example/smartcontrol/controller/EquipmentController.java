package com.example.smartcontrol.controller;

import com.example.smartcontrol.domain.equipment.AirRequestDTO;
import com.example.smartcontrol.domain.equipment.AirResponseDTO;
import com.example.smartcontrol.domain.rows.Rows;
import com.example.smartcontrol.domain.rows.RowsRequestDTO;
import com.example.smartcontrol.domain.user.LoginReponseDTO;
import com.example.smartcontrol.repositories.AirRepository;
import com.example.smartcontrol.domain.equipment.EquipmentAir;
import com.example.smartcontrol.repositories.RowRepository;
import com.example.smartcontrol.services.SerialCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("air")
public class EquipmentController {
    @Autowired
    private AirRepository repository;
    @Autowired
    private RowRepository rowRepository;
    @Autowired
    private SerialCommunicationService serialService;

    @GetMapping("/test/turnOn")
    public String testTurnOn() {
        List<Integer> numbers = Arrays.asList(1234, 5678, 9101);
        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            sb.append(number).append(","); // Usando vírgula como delimitador
        }
        String dataToSend = sb.toString();
        serialService.sendMessageToSerialPort(dataToSend);
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

    @PostMapping("/row")
    public ResponseEntity saveRow(@RequestBody RowsRequestDTO data){
        Rows rows = new Rows(data);
        rowRepository.save(rows);
        return ResponseEntity.ok(rows);
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

    @PutMapping("/{id}/turnOn")
    public ResponseEntity updateTurnOn(@PathVariable String id){
        Optional<EquipmentAir> OptionalEquipmentAir = repository.findById(id);
        if(OptionalEquipmentAir.isPresent()){
            EquipmentAir equipmentAir = OptionalEquipmentAir.get();
            String model = equipmentAir.getModel();
            List<Rows> rowsList = rowRepository.findByModel(model);
            Rows newRow = rowsList.get(0);
            List<Integer> row = newRow.getRowon();
            StringBuilder sb = new StringBuilder();
            for (Integer number : row) {
                sb.append(number).append(","); // Usando vírgula como delimitador
            }
            String dataToSend = sb.toString();
            serialService.sendMessageToSerialPort(dataToSend);
            equipmentAir.setActive(true);
            return ResponseEntity.ok(equipmentAir);

        }
        return ResponseEntity.notFound().build();
    }
}
