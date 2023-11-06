package com.example.smartcontrol.equipment;

public record AirConditioningDTO(String id, String model, String description, Boolean condition, Boolean active) {
    public AirConditioningDTO(EquipmentAir air){
        this(air.getId(),air.getModel(),air.getDescription(),air.getCondition(),air.getActive());
    }
}
