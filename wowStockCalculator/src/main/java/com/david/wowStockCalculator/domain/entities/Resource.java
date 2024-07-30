package com.david.wowStockCalculator.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_id_seq")
    private Long id;

    private String name;

    @Column(name = "quality", columnDefinition = "varchar(8) default 'NONE'")
    @Enumerated(value = EnumType.STRING)
    private Quality quality = Quality.NONE;

}
