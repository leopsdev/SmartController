package com.example.smartcontrol.domain.equipment;


public record AirResponseDTO(String id,
                             String model,
                             String description,
                             Boolean condition,
                             Boolean active,
                             String division,
                             String segment,
                             Integer command) {
    public AirResponseDTO(EquipmentAir air){
        this(air.getId(),
                air.getModel(),
                air.getDescription(),
                air.getCondition(),
                air.getActive(),
                air.getDivision(),
                air.getSegment(),
                air.getCommand());
    }
}
