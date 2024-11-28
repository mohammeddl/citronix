package systems.citronix.demo.exception;

import java.lang.RuntimeException;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
    
}
