package com.yguo57.anno;

import com.yguo57.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {StateValidation.class}
)

public @interface State {
    // provide validation fail msg
    String message() default "state params only be sent or draft";

    // assign groups
    Class<?>[] groups() default {};

    // payload
    Class<? extends Payload>[] payload() default {};
}
