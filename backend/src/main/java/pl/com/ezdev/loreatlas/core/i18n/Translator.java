package pl.com.ezdev.loreatlas.core.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translator {

    private static MessageSource messageSource;

    public Translator(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String translate(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}