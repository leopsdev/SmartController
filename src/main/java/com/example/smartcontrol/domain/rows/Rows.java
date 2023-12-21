package com.example.smartcontrol.domain.rows;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private List<Integer> rowon;
    private List<Integer> rowoff;

    public Rows(RowsRequestDTO data){
        this.model = data.model();
        this.rowoff = data.rowoff();
        this.rowon = data.rowon();
    }
}





