package ua.hehmde.kvjewelry.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.hehmde.kvjewelry.util.LocaleUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {

    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class PasswordValidator
        implements ConstraintValidator<ValidPassword, String> {

    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    LocaleUtil localeUtil;

    private static final String PASSWORD_PATTERN = "password.regexp";
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context){
        return (validatePassword(password));
    }
    private boolean validatePassword(String password) {
        pattern = Pattern.compile(localeUtil.getLocalizedProperty(PASSWORD_PATTERN));
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}