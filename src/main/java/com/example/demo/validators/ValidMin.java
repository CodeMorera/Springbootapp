package com.example.demo.validators;
import com.example.demo.domain.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;// Used to create custom validation logic.
import javax.validation.ConstraintValidatorContext;//provides error messages

//validates objects of type Part with a custom annotation VMininum
public class ValidMin implements ConstraintValidator<VMinimum, Part> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext thisContext;

    @Override
    public void initialize(VMinimum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part item, ConstraintValidatorContext constraintValidatorContext) {
        return item.getInv() > item.getMinimumInv();//The actual validation logic.
    }
}

