package com.productdock.library.inventory.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InventoryException extends RuntimeException {

    public InventoryException(String message) {
        super(message);
    }
}
