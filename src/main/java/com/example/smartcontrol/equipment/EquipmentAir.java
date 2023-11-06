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

}
