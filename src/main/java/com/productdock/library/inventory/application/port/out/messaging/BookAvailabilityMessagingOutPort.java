package com.productdock.library.inventory.application.port.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.domain.Inventory;

import java.util.concurrent.ExecutionException;

public interface BookAvailabilityMessagingOutPort {

    void sendMessage(Inventory inventory) throws ExecutionException, InterruptedException, JsonProcessingException;
}
