package systems.citronix.demo.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systems.citronix.demo.validation.ValidPlantingDate;

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

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    public int getAge() {
        if (plantingDate == null) {
            return 0;
        }
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }

    public double calculateProductivity() {
        int age = getAge();
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
        return getAge() <= 20;
    }
}
