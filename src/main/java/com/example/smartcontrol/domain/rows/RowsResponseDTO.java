package com.example.smartcontrol.domain.rows;

import java.util.List;

public record RowsResponseDTO(String model,
                              String id,
                              String rowon,
                              String rowoff) {

    public RowsResponseDTO (Rows rows){
        this(rows.getId(),rows.getModel(), rows.getRowon(), rows.getRowoff());
    }
}
