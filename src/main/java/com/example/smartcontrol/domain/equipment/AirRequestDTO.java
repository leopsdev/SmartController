package com.example.smartcontrol.domain.equipment;


public record AirRequestDTO(String id,
                            String model,
                            String description,
                            Boolean active,
                            Boolean condition,
                            String division,
                            String segment,
                            Integer command) {

}
