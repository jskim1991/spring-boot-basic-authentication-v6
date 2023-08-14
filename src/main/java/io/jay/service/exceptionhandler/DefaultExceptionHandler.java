package io.jay.service.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleException(AuthenticationException e) {
        var p = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        p.setTitle("Failed to authenticate");
        p.setInstance(URI.create("/api/test"));
        p.setType(URI.create("https://example.com/docs/unauthorized"));
        return p;
    }
}
