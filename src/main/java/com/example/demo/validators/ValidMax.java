package com.example.demo.validators;

import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidMax implements ConstraintValidator<VMaximum, Part> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext thisContext;

    @Override
    public void initialize(VMaximum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part item, ConstraintValidatorContext constraintValidatorContext) {
        return item.getInv() <= item.getMaximumInv();//The actual validation logic.
    }
}
