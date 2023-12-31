package com.example.smartcontrol.domain.equipment;

import java.util.ArrayList;
import java.util.UUID;

public record SegmentDTO(UUID id, String name, ArrayList<AirResponseDTO> equipment) {
}
