package com.example.smartcontrol.domain.rows;

import java.util.List;

public record RowsRequestDTO(String id,
                             String model,
                             List<Integer> rowon,
                             List<Integer> rowoff) {
}
