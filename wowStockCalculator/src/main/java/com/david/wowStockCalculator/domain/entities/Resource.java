package com.david.wowStockCalculator.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resource", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "quality" }) })
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_id_seq")
    private Long id;

    private String name;

    private Quality quality;

    private Integer onStock;

    public void addToStock(Integer amount){
        onStock += amount;
    };
}
