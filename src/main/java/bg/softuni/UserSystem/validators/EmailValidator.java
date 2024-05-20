package bg.softuni.UserSystem.validators;

import bg.softuni.UserSystem.annotations.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmailValidator implements ConstraintValidator<Email, String> {


    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        List<Character> invalidStartEndOfEmail = List.of('~', '`','!', '@', '#', '$', '%',
                '^','&', '*', '(', ')', '-', '_', '=', '+', '№', '[', '{', '}', ']', '\\', '|', ';',
                ':', '\'', '\"', ',', '<', '.', '>', '/', '?');

        if (!email.matches("[a-zA-Z0-9]+([-_.][A-Za-z0-9]+)*@[a-z-]+(\\.[a-z]+)+")) {
            return false;
        }

        if (invalidStartEndOfEmail.contains(email.charAt(0))) {
            return false;
        }

        if (invalidStartEndOfEmail.contains(email.charAt(email.length() - 1))) {
            return false;
        }

        return true;
    }
}
