package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQueryParameters {
    String message() default "Invalid combo of query parameters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
