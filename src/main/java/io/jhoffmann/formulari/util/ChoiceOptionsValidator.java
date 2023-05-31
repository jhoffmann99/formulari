package io.jhoffmann.formulari.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChoiceOptionsValidator implements ConstraintValidator<ChoiceOptions, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            value.split(value);

            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
