package com.productdock.library.inventory.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory/books")
@Slf4j
public record InventoryRecordApi(InventoryRecordService inventoryRecordService) {

    @GetMapping("/{bookId}")
    public int getAvailableBooksCount(@PathVariable("bookId") String bookId) {
        log.debug("GET request received - api/inventory/books/{}", bookId);
        return inventoryRecordService.getAvailableBooksCount(bookId);
    }
}
