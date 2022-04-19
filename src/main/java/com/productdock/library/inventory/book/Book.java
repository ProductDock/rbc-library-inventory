package com.productdock.library.inventory.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookshelf")
@Data
@AllArgsConstructor
public class Book {
    @Id
    private String bookId;
    private int bookCopies;
    private int reservedBooks;
    private int rentedBooks;

    public void incrementReservedBooks() {
        reservedBooks++;
    }

    public void decrementReservedBooks() {
        reservedBooks--;
    }

    public void incrementRentedBooks() {
        rentedBooks++;
    }

    public void decrementRentedBooks() {
        rentedBooks--;
    }
}
