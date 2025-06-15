package co.devskills.springbootboilerplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PresentationNotFoundException extends RuntimeException {

    public PresentationNotFoundException(String message)
    {
        super(message);
    }
}