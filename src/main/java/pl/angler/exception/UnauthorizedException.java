package pl.angler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String s) {
        super(s);
    }

    public UnauthorizedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UnauthorizedException(Throwable throwable) {
        super(throwable);
    }
}
