package com.citronix.demo.model;

import com.citronix.demo.model.TreeHarvestDetail;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;

    @NotNull(message = "Harvest date is required")
    private LocalDate date;

    @Positive(message = "Harvest quantity must be positive")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<TreeHarvestDetail> treeHarvestDetails;
}
