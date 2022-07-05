package com.productdock.library.inventory.adapter.out.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookshelf")
@Data
@AllArgsConstructor
@Builder
public class InventoryRecordEntity {

    @Id
    private String id;
    private String bookId;
    private int bookCopies;
    private int reservedBooks;
    private int rentedBooks;
}
