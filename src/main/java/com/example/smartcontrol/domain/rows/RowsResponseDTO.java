package com.example.smartcontrol.domain.rows;

import java.util.List;

public record RowsResponseDTO(String model,
                              String id,
                              List<Integer> rowon,
                              List<Integer> rowoff) {

    public RowsResponseDTO (Rows rows){
        this(rows.getId(),rows.getModel(), rows.getRowon(), rows.getRowoff());
    }
}
