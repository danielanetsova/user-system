package bg.softuni.UserSystem.validators;

import bg.softuni.UserSystem.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;
    private boolean containsDigits;
    private boolean containsUpperCase;
    private boolean containsLowerCase;
    private boolean containsSpecialSymbol;


    @Override
    public void initialize(Password constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.containsDigits = constraintAnnotation.containsDigits();
        this.containsUpperCase = constraintAnnotation.containsUpperCase();
        this.containsLowerCase = constraintAnnotation.containsLowerCase();
        this.containsSpecialSymbol = constraintAnnotation.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password.length() < minLength || password.length() > maxLength) {
            return false;
        }

        if (containsDigits && !password.matches(".*\\d.*")) {
            return false;
        }

        if (containsUpperCase && !password.matches(".*[A-Z].*")) {
            return false;
        }

        if (containsLowerCase && !password.matches(".*[a-z].*")) {
            return false;
        }

        if (containsSpecialSymbol && !password.matches(".*[!@#$%^&*()_+<>?].*")) {
            return false;
        }

        return true;
    }
}
