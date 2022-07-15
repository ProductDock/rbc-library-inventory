package com.productdock.library.inventory.application.port.in;

import com.productdock.library.inventory.domain.BookRentals;

public interface UpdateBookStatusUseCase {

    void updateBookStatus(BookRentals bookRentals);
}
