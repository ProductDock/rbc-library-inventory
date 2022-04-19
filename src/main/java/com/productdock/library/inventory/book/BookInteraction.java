package com.productdock.library.inventory.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class BookInteraction {
    private String userEmail;
    private Date date;
}
