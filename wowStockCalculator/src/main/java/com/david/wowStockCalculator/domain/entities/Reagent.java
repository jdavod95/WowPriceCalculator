package com.david.wowStockCalculator.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reagent")
public class Reagent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reagent_id_seq")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private Integer amount;
}
