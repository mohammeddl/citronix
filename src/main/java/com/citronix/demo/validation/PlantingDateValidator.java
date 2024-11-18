package com.citronix.demo.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlantingDateValidator implements ConstraintValidator<ValidPlantingDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate plantingDate, ConstraintValidatorContext context) {
        if (plantingDate == null) return false;

        int month = plantingDate.getMonthValue();
        return month >= 3 && month <= 5; 
    }
    
}
