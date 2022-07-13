package com.productdock.library.inventory.application.port.in;

import com.productdock.library.inventory.domain.RentalRecord;

public interface UpdateBookStatusUseCase {

    void updateBookStatus(RentalRecord rentalRecord);
}
