package com.productdock.library.inventory.adapter.out.kafka.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
public class BookSubscriptionMessage implements Serializable {

    public final String bookId;
    public final String userId;

}
