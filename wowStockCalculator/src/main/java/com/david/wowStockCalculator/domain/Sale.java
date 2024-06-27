package com.david.wowStockCalculator.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_id_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;

    private Integer amount;

    private Integer cost;

}
