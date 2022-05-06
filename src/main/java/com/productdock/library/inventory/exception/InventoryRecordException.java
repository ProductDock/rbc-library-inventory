package com.productdock.library.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InventoryRecordException extends RuntimeException {

    public InventoryRecordException(String message) {
        super(message);
    }
}
