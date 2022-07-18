package com.productdock.library.inventory.application.port.out.persistence;

import com.productdock.library.inventory.domain.Inventory;

import java.util.Optional;

public interface InventoryRecordsPersistenceOutPort {

    Optional<Inventory> findByBookId(String bookId);

    void save(Inventory book);
}
