package com.productdock.library.inventory.adapter.in.web;

import com.productdock.library.inventory.book.InventoryRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory/book")
@Slf4j
public record GetAvailableBooksCountApi(InventoryRecordService inventoryRecordService) {

    @GetMapping("/{bookId}")
    public int getAvailableBooksCount(@PathVariable("bookId") String bookId) {
        log.debug("GET request received - api/inventory/book/{}", bookId);
        return inventoryRecordService.getAvailableBooksCount(bookId);
    }
}
