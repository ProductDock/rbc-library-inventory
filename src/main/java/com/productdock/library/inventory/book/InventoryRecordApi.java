package com.productdock.library.inventory.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory/books")
public record InventoryRecordApi(InventoryRecordService inventoryRecordService) {

    @GetMapping("/{bookId}")
    public int getAvailableBooksCount(@PathVariable("bookId") String bookId){
        return inventoryRecordService.getAvailableBooksCount(bookId);
    }
}
