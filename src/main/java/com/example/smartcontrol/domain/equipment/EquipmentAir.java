package com.example.smartcontrol.domain.equipment;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "air")
@Entity(name = "air")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class EquipmentAir {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String model;
    private String description;
    private Boolean active;
    private Boolean condition;
    private String division;
    private String segment;
    private Integer command;

    public EquipmentAir(AirRequestDTO data){
        this.active = data.active();
        this.condition = data.condition();
        this.description = data.description();
        this.model = data.model();
        this.division = data.division();
        this.segment = data.segment();
        this.command = data.command();
    }

}
