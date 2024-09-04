package com.example.demo.validators;

import javax.validation.Constraint; //indicates that an annotation is a valid constraint
import javax.validation.Payload; //Allows the carrying of additional information about the validation.
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ValidMin.class)//Will use the rules defined in 'ValidMin' to check if valid
@Target({ElementType.TYPE})//Just means the annotation can be applied to target.
@Retention(RetentionPolicy.RUNTIME)//annotation will be available during runtime
//Error message for inventory if inventory falls below set minimum when updating count.
public @interface VMinimum {
    String message() default "Invalid count! Below minimum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};//set as an empty array, carries additional information of the example, severity level
}
