package com.productdock.library.inventory.application.port.in;

import com.productdock.library.inventory.domain.BookRentals;

public interface UpdateBookStockUseCase {

    void updateBookStock(BookRentals bookRentals);
}
