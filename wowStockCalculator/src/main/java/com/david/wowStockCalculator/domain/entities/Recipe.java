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

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinTable(
            name = "required_reagent",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "reagent_id")
    )
    private List<Reagent> requiredReagents = new LinkedList<>();

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinTable(
            name = "resulting_reagent",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "reagent_id")
    )
    private List<Reagent> resultingReagents = new LinkedList<>();

    private Integer difficulty;

    @ElementCollection(targetClass = CraftingStat.class)
    @CollectionTable(name = "crafting_stat", joinColumns = @JoinColumn(name = "recipe_id"))
    @Enumerated(EnumType.STRING)
    private List<CraftingStat> craftingStats;
}
