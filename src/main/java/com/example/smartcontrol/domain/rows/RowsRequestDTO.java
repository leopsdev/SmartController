package com.example.smartcontrol.domain.rows;

import java.util.List;

public record RowsRequestDTO(String id,
                             String model,
                             String rowon,
                             String rowoff) {
}
