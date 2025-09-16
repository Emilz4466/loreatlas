package pl.com.ezdev.loreatlas.core.exception;


import pl.com.ezdev.loreatlas.core.base.BaseEntity;
import pl.com.ezdev.loreatlas.core.i18n.Translator;

public class EntityNotFoundException extends RuntimeException {

    public static final String KEY = "error.entityNotFound";

    public EntityNotFoundException(Class<? extends BaseEntity> entityClass,
                                   String fieldName,
                                   Object fieldValue) {
        super(Translator.translate(
                KEY,
                entityClass.getSimpleName(),
                fieldName,
                fieldValue
        ));
    }
}