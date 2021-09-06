package ua.hehmde.kvjewelry.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleUtil {
    private final MessageSource messageSource;

    public LocaleUtil(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public static Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }

    public String getLocalizedProperty(String id) {
        return messageSource.getMessage(id, null, getCurrentLocale());
    }
}
