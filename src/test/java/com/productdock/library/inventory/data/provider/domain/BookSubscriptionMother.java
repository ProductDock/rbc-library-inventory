package com.productdock.library.inventory.data.provider.domain;

import com.productdock.library.inventory.domain.BookSubscription;

public class BookSubscriptionMother {

    private static final String defaultBookId = "1";

    private static final String defaultUserId = "userEmail";

    public static BookSubscription bookSubscription() {
        return bookSubscriptionBuilder().build();
    }

    public static BookSubscription.BookSubscriptionBuilder bookSubscriptionBuilder() {
        return BookSubscription.builder()
                .bookId(defaultBookId)
                .userId(defaultUserId);
    }

}
