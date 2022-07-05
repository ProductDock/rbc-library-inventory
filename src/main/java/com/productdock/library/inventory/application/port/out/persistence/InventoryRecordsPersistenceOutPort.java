package com.productdock.library.inventory.application.port.out.persistence;

import com.productdock.library.inventory.domain.Inventory;

public interface InventoryRecordsPersistenceOutPort {

    Inventory getInventoryFrom(String bookId);

    void saveInventoryRecord(Inventory book);
}
