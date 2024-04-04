package com.productdock.library.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Slf4j
public class BookSubscription {

    private String bookId;
    private String userId;
    private Date createdDate;
}
