package com.productdock.library.inventory.data.provider.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionsEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class BookSubscriptionsEntityMother {

    private static final String defaultId = "1111111";
    private static final String defaultBookId = "1";
    private static final String defaultUserId = "userEmail";

    public static BookSubscriptionsEntity bookSubscriptionsEntity() {
        return bookSubscriptionsEntityBuilder().build();
    }

    public static BookSubscriptionsEntity.BookSubscriptionsEntityBuilder bookSubscriptionsEntityBuilder() {
        return BookSubscriptionsEntity.builder()
                .id(defaultId)
                .bookId(defaultBookId)
                .subscribers(new ArrayList<>(Arrays.asList(defaultUserId)));
    }

}
