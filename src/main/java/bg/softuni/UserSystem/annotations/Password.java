package bg.softuni.UserSystem.annotations;

import bg.softuni.UserSystem.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Password {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default 6;
    int maxLength() default 50;
    boolean containsDigits() default true;
    boolean containsUpperCase() default true;
    boolean containsLowerCase() default true;
    boolean containsSpecialSymbol() default true;
}
