package com.productdock.library.inventory.adapter.out.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("subscriptions")
@Data
@AllArgsConstructor
@Builder
public class BookSubscriptionsEntity {

    @Id
    private String id;
    private String bookId;
    private List<String> subscribers;
}
