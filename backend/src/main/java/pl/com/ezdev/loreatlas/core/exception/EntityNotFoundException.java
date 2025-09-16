package pl.com.ezdev.loreatlas.core.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String source) {
        super(source + message + " not found.");
    }
}
