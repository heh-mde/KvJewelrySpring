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
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface ValidName {
    LocaleUtil LOCALE_UTIL = null;

    String message() default "Invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class NameValidator
        implements ConstraintValidator<ValidName, String> {

    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    LocaleUtil localeUtil;

    private static final String NAME_PATTERN = "name.regexp";

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return (validateName(name));
    }

    private boolean validateName(String name) {
        pattern = Pattern.compile(localeUtil.getLocalizedProperty(NAME_PATTERN));
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
}