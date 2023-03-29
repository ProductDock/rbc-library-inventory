package com.productdock.library.inventory.data.provider.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.InsertBookMessage;

public class InsertBookMessageMother {

    private static final String defaultBookId = "1";
    private static final int defaultBookCopies = 1;

    public static InsertBookMessage insertBookMessage() { return insertBookMessageBuilder().build(); }

    public static InsertBookMessage.InsertBookMessageBuilder insertBookMessageBuilder(){
        return InsertBookMessage.builder()
                .bookId(defaultBookId)
                .bookCopies(defaultBookCopies);
    }

}
