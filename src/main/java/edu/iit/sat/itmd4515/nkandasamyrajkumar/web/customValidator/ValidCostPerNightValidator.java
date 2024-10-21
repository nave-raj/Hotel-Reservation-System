/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.web.customValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author naveenarajkumar
 */


public class ValidCostPerNightValidator implements ConstraintValidator<ValidCostPerNight, Double> {

    private double min;
    private double max;

    /**
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValidCostPerNight constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    /**
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= min && value <= max;
    }
}

