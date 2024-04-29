package com.productdock.library.inventory.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SubscriptionException extends RuntimeException {

    public SubscriptionException(String message) {
        super(message);
    }
}
