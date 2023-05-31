package io.jhoffmann.formulari.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ChoiceOptionsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChoiceOptions {
    String message() default "invalid options";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
