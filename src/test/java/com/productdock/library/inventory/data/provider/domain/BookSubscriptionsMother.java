package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.BookSubscriptions;

import java.util.ArrayList;
import java.util.Arrays;

public class BookSubscriptionsMother {

    private static final String defaultBookId = "1";

    private static final String defaultUserId = "userEmail";

    public static BookSubscriptions bookSubscriptions() {
        return bookSubscriptionsBuilder().build();
    }

    public static BookSubscriptions.BookSubscriptionsBuilder bookSubscriptionsBuilder() {
        return BookSubscriptions.builder()
                .bookId(defaultBookId)
                .subscribers(new ArrayList<>(Arrays.asList(defaultUserId)));
    }

}
