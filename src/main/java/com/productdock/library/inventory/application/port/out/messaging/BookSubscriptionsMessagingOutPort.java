package com.productdock.library.inventory.application.port.out.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.domain.BookSubscription;

import java.util.concurrent.ExecutionException;

public interface BookSubscriptionsMessagingOutPort {

    void sendMessage(BookSubscription subscriptions) throws ExecutionException, InterruptedException, JsonProcessingException;
}
