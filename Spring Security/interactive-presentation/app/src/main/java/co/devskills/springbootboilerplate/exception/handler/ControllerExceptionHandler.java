package co.devskills.springbootboilerplate.exception.handler;


import co.devskills.springbootboilerplate.exception.PresentationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({ PresentationNotFoundException.class, UsernameNotFoundException.class })
    public @ResponseBody ResponseEntity<String> handleRuntimeException(RuntimeException exception)
    {
        log.info(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
