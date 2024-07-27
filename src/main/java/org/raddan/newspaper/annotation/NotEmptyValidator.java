package org.raddan.newspaper.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.raddan.newspaper.exception.custom.FieldEmptyException;

/**
 * @author Alexander Dudkin
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    private String message;

    @Override
    public void initialize(NotEmpty constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            throw new FieldEmptyException(message);
        }
        return true;
    }
}
