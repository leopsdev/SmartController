package com.example.smartcontrol.equipment;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "air")
@Entity(name = "air")
@Getter
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

    public EquipmentAir(AirRequestDTO data){
        this.active = data.active();
        this.condition = data.condition();
        this.description = data.description();
        this.model = data.model();
    }

}
