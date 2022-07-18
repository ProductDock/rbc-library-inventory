package com.productdock.library.inventory.application.port.in;

public interface GetAvailableBooksCountQuery {

    int getAvailableBooksCount(String bookId);
}
