package com.productdock.library.inventory.config.dbmigrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.adapter.out.mongo.entity.InventoryRecordEntity;
import com.productdock.library.inventory.config.coverage.Generated;

@Generated
@ChangeLog(order = "001")
public class DatabaseInitChangeLog {

    @ChangeSet(order = "001", id = "init_inventory_records", author = "pd")
    public void initInventoryRecords(InventoryRecordRepository inventoryRecordRepository) {

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("1")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("2")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("3")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("4")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("5")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("6")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("7")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("8")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("9")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("10")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("11")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("12")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("13")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("14")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("15")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("16")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("17")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("18")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("19")
                .bookCopies(2)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("20")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("21")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("22")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("23")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("24")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("25")
                .bookCopies(2)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("26")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("27")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("28")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("29")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("30")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("31")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("32")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("33")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("34")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("35")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("36")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("37")
                .bookCopies(8)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("38")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("39")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("40")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("41")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("42")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("43")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("44")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("45")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("46")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("47")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("48")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("49")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("50")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("51")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("52")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("53")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("54")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("55")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("56")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("57")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("58")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("59")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("60")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("61")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("62")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("63")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("64")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("65")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("66")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("67")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("68")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("69")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("70")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(1)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("71")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("72")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());
    }

    @ChangeSet(order = "002", id = "add_two_new_books_to_inventory", author = "pd")
    public void addTwoNewBooksToInventory(InventoryRecordRepository inventoryRecordRepository) {
        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("74")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("75")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());
    }

    @ChangeSet(order = "003", id = "add_four_new_books_to_inventory", author = "pd")
    public void addFourNewBooksToInventory(InventoryRecordRepository inventoryRecordRepository) {
        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("76")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("77")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("78")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("79")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());
    }

    @ChangeSet(order = "004", id = "add_missing_books_to_inventory", author = "pd")
    public void addMissingBooksToInventory(InventoryRecordRepository inventoryRecordRepository) {
        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("80")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("81")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("82")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("83")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("84")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("85")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());

        inventoryRecordRepository.save(InventoryRecordEntity.builder()
                .bookId("86")
                .bookCopies(1)
                .reservedBooks(0)
                .rentedBooks(0)
                .build());
    }
}
