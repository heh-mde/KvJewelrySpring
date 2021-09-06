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
@Constraint(validatedBy = LoginValidator.class)
@Documented
public @interface ValidLogin {
    LocaleUtil LOCALE_UTIL = null;

    String message() default "Invalid login";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class LoginValidator
        implements ConstraintValidator<ValidLogin, String> {

    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    LocaleUtil localeUtil;

    private static final String LOGIN_PATTERN = "login.regexp";

    @Override
    public void initialize(ValidLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return (validateLogin(login));
    }

    private boolean validateLogin(String login) {
        pattern = Pattern.compile(localeUtil.getLocalizedProperty(LOGIN_PATTERN));
        matcher = pattern.matcher(login);
        return matcher.matches();
    }
}