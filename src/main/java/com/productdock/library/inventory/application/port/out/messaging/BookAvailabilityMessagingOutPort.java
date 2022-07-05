package com.productdock.library.inventory.application.port.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityMessage;

import java.util.concurrent.ExecutionException;

public interface BookAvailabilityMessagingOutPort {

    void sendMessage(BookAvailabilityMessage bookAvailabilityMessage) throws ExecutionException, InterruptedException, JsonProcessingException;
}
