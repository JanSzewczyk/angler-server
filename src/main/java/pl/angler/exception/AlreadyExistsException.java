package pl.angler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException  {
    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String s) {
        super(s);
    }

    public AlreadyExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AlreadyExistsException(Throwable throwable) {
        super(throwable);
    }
}
