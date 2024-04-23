package com.productdock.library.inventory.adapter.out.kafka.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
public class ActionMessage implements Serializable {

    public final String type;
    public final String target;

}
