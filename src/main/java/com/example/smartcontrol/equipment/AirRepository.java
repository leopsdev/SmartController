package com.example.smartcontrol.equipment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirRepository extends JpaRepository<EquipmentAir,String> {
    List<EquipmentAir> findByDivision(String division);
}
