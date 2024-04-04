package com.productdock.library.inventory.adapter.out.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("subscriptions")
@Data
@AllArgsConstructor
@Builder
@CompoundIndex(def = "{'bookId': 1, 'userId': 1}", unique = true)
public class BookSubscriptionEntity {

    @Id
    private String id;
    private String bookId;
    private String userId;
    @CreatedDate
    private Date createdDate;

}
