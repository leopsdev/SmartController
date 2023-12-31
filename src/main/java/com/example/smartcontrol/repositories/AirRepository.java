package com.example.smartcontrol.repositories;

import com.example.smartcontrol.domain.equipment.EquipmentAir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirRepository extends JpaRepository<EquipmentAir,String> {
    List<EquipmentAir> findByDivision(String division);
    List<EquipmentAir> findByDivisionAndSegment(String division,String segment);
}
