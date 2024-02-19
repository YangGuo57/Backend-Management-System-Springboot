package com.yguo57.validation;

import com.yguo57.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {
    /**
     * @param value   data to be validated
     * @param context context in which the constraint is evaluated
     * @return if false, validation failed and vice versa
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // provide validation rules
        if (value == null) {
            return false;
        }
        return value.equals("sent") || value.equals("draft");
    }
}
