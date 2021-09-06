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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    LocaleUtil localeUtil;

    private static final String EMAIL_PATTERN = "email.regexp";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return (validateEmail(email));
    }

    private boolean validateEmail(String email) {
        pattern = Pattern.compile(localeUtil.getLocalizedProperty(EMAIL_PATTERN));
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}