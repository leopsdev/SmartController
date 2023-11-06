package com.example.smartcontrol.equipment;

import java.util.ArrayList;
import java.util.UUID;

public record DivisionDTO(UUID id, String name, ArrayList<SegmentDTO> rooms) {
}
