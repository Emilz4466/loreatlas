package pl.com.ezdev.loreatlas.cmm.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String source) {
        super(source + message + " not found.");
    }
}
