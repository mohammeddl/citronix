package systems.citronix.demo.model;

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

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreeHarvestDetail> treeHarvestDetails;

    @OneToOne(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sale sale;
}
