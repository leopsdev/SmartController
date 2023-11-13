package com.example.smartcontrol.equipment;


public record AirRequestDTO(String id,
                            String model,
                            String description,
                            Boolean active,
                            Boolean condition,
                            String division,
                            String segment) {

}
