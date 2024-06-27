package com.david.wowStockCalculator.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sale")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_id_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    //@JsonProperty("timeOfSale")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;

    private Integer amount;

    private Integer cost;

}
