package com.david.wowStockCalculator.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_seq")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<Reagent> reagents = new LinkedList<>();

    private Integer difficulty;

    @Column(name = "result_amount", columnDefinition = "integer default 0")
    private Integer resultAmount;

    @ElementCollection(targetClass = CraftingStat.class)
    @CollectionTable(name = "crafting_stat", joinColumns = @JoinColumn(name = "recipe_id"))
    @Enumerated(EnumType.STRING)
    private List<CraftingStat> craftingStats;
}
