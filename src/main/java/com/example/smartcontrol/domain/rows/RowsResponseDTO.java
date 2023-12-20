package com.example.smartcontrol.domain.rows;

public record RowsResponseDTO(String model,
                              String rowOn,
                              String RowOff) {

    public RowsResponseDTO (Rows rows){
        this(rows.getModel(), rows.getRowOn(), rows.getRowOff());
    }
}
