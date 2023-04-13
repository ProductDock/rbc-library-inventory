package com.productdock.library.inventory.application.port.in;

import com.productdock.library.inventory.domain.Inventory;

public interface InsertBookUseCase {

    void insertBook(Inventory bookInventory);

}
