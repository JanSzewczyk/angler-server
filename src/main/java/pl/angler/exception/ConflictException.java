package pl.angler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConflictException extends RuntimeException {
    public ConflictException() {
    }

    public ConflictException(String s) {
        super(s);
    }

    public ConflictException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConflictException(Throwable throwable) {
        super(throwable);
    }
}
