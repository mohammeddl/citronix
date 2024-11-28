package systems.citronix.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field name cannot be blank")
    private String name;

    @DecimalMin(value = "0.1", message = "Field surface must be at least 0.1 hectares")
    @Column(nullable = false)
    private Double surface;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @JsonIgnore
    private Farm farm;

    @OneToMany(mappedBy = "field")
    private List<Harvest> harvests;
}
