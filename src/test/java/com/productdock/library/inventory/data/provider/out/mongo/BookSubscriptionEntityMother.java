package com.productdock.library.inventory.data.provider.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionEntity;

public class BookSubscriptionEntityMother {

    private static final String defaultId = "1111111";
    private static final String defaultBookId = "1";
    private static final String defaultUserId = "userEmail";

    public static BookSubscriptionEntity bookSubscriptionEntity() {
        return bookSubscriptionEntityBuilder().build();
    }

    public static BookSubscriptionEntity.BookSubscriptionEntityBuilder bookSubscriptionEntityBuilder() {
        return BookSubscriptionEntity.builder()
                .id(defaultId)
                .bookId(defaultBookId)
                .userId(defaultUserId);
    }

}
