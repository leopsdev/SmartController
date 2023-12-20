package com.example.smartcontrol.domain.rows;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "rows")
@Entity(name = "rows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Rows {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String rowOn;
    private String rowOff;

    public Rows(RowsRequestDTO data){
        this.model = data.model();
        this.rowOff = data.RowOff();
        this.rowOn = data.rowOn();
    }
}





