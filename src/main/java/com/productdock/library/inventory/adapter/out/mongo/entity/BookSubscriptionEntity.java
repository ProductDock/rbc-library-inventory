package com.productdock.library.inventory.adapter.out.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("subscriptions")
@Data
@AllArgsConstructor
@Builder
public class BookSubscriptionEntity {

    @Id
    private String id;
    private String bookId;
    private String userId;
}
