package com.citronix.demo.model;

import java.time.LocalDate;

import com.citronix.demo.validation.ValidPlantingDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidPlantingDate
    @NotNull(message = "Planting date is required")
    private LocalDate plantingDate;

    @PositiveOrZero(message = "Tree age must be zero or positive")
    private int age;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    public double calculateProductivity() {
        if (age < 3) {
            return 2.5; 
        } else if (age <= 10) {
            return 12.0; 
        } else if (age <= 20) {
            return 20.0; 
        } else {
            return 0.0; 
        }
    }

    public boolean isProductive() {
        return age <= 20;
    }
}
