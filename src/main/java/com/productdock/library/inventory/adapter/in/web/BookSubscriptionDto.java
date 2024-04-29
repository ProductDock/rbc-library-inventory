package com.productdock.library.inventory.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Builder
@AllArgsConstructor
public class BookSubscriptionDto {

    public String bookId;
    public String userId;
    public Date createdDate;

}
