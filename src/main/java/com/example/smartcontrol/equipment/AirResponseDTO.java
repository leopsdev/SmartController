package com.example.smartcontrol.equipment;

public record AirResponseDTO(String id, String model, String description, Boolean condition, Boolean active) {
    public AirResponseDTO(EquipmentAir air){
        this(air.getId(),air.getModel(),air.getDescription(),air.getCondition(),air.getActive());
    }
}
