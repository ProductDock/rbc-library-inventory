package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.UpdateBookStatusUseCase;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.BookRentals;
import com.productdock.library.inventory.domain.RentalStatus;
import com.productdock.library.inventory.domain.exception.InventoryException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateBookStatusService implements UpdateBookStatusUseCase {

    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    private BookAvailabilityMessagingOutPort bookAvailabilityPublisher;

    @Override
    @SneakyThrows
    public void updateBookStatus(BookRentals bookRentals) {
        log.debug("Update book state for book {} with rental record: rents count - {}, reservations count - {}", bookRentals.getBookId(), bookRentals.getRecordsCount(RentalStatus.RENTED), bookRentals.getRecordsCount(RentalStatus.RESERVED));

        var inventory = inventoryRecordRepository.findByBookId(bookRentals.getBookId()).orElseThrow(() -> new InventoryException("Book does not exist in inventory!"));
        inventory.updateStateWith(bookRentals);
        inventoryRecordRepository.save(inventory);
        bookAvailabilityPublisher.sendMessage(inventory);
    }
}
