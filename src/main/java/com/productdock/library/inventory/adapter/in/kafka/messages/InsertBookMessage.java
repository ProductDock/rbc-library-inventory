package com.productdock.library.inventory.adapter.in.kafka.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class InsertBookMessage {

    public final String bookId;
    public final int bookCopies;
}
