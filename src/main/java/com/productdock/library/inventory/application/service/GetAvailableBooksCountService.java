package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GetAvailableBooksCountService implements GetAvailableBooksCountQuery {

    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Override
    public int getAvailableBooksCount(String bookId) {
        var book = inventoryRecordRepository.getInventoryFrom(bookId);
        return book.getAvailableBooksCount();
    }
}
