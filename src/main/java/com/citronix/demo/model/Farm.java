package com.citronix.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Farm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Farm name cannot be blank")
    private String name;

    @NotBlank(message = "Localization cannot be blank")
    private String localization;

    @DecimalMin(value = "0.1", message = "Farm surface must be greater than 0.1 hectares")
    @Column(nullable = false)
    private double surface;

    @PastOrPresent(message = "Creation date must be in the past or present")
    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Field> fields;
}