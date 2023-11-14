package com.example.smartcontrol.domain.equipment;

import java.util.ArrayList;
import java.util.UUID;

public record DivisionDTO(UUID id, String name, ArrayList<SegmentDTO> rooms) {
}
