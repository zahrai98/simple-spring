package com.example.sample.address.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StreetValidator implements ConstraintValidator<ValidStreet, String> {

    @Override
    public boolean isValid(String street, ConstraintValidatorContext context) {
        // Custom validation logic
        return street != null && !street.contains(" ") && street.length() >= 4;
    }
}
