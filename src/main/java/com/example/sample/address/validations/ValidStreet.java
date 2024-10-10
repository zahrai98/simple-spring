package com.example.sample.address.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StreetValidator.class) // Link to the validator class
@Target({ ElementType.FIELD, ElementType.PARAMETER }) // Where the annotation can be used
@Retention(RetentionPolicy.RUNTIME) // How long the annotation is retained
public @interface ValidStreet {

    String message() default "Invalid street"; // Default error message

    Class<?>[] groups() default {}; // Used for grouping constraints

    Class<? extends Payload>[] payload() default {}; // Used to attach additional metadata
}