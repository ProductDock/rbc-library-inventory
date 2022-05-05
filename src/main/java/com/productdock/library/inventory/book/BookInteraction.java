package com.productdock.library.inventory.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class BookInteraction implements Serializable {

    private String userEmail;
    private Date date;
}
