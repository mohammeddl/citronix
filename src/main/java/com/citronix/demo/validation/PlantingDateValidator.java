package com.citronix.demo.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlantingDateValidator implements ConstraintValidator<ValidPlantingDate, LocalDate> {

    public boolean isValid(LocalDate plantingDate, ConstraintValidatorContext context) {
        System.out.println("Validating plantingDate: " + plantingDate);
        if (plantingDate == null) return false;
    
        int month = plantingDate.getMonthValue();
        return month >= 3 && month <= 5;
    }
    
}
