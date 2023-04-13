package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.InsertBookUseCase;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsertBookService implements InsertBookUseCase {

    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Override
    public void insertBook(Inventory bookInventory) {
        inventoryRecordRepository.save(bookInventory);
    }
}
