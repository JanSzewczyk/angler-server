package pl.angler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException{
    public ServerException() {
    }

    public ServerException(String s) {
        super(s);
    }

    public ServerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ServerException(Throwable throwable) {
        super(throwable);
    }
}
