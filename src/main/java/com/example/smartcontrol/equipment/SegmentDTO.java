package com.example.smartcontrol.equipment;

import java.util.ArrayList;
import java.util.UUID;

public record SegmentDTO(UUID id, String name, ArrayList<AirConditioningDTO> equipment) {
}
