package com.productdock.library.inventory.adapter.out.kafka.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
public class BookSubscriptionMessage implements Serializable {

    public final String title;
    public final String description;
    public final String userId;
    public final ActionMessage action;
}
