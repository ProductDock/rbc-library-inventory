package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.UpdateBookStatusUseCase;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityMessage;
import com.productdock.library.inventory.domain.RentalRecord;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateBookStatusService implements UpdateBookStatusUseCase {

    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    private BookAvailabilityMessagingOutPort bookAvailabilityMessagingOutPort;

    @Override
    @SneakyThrows
    public void updateBookState(RentalRecord rentalRecord) {
        log.debug("Update book state for book {} with rental record: rents count - {}, reservations count - {}", rentalRecord.getBookId(), rentalRecord.getRentsCount(), rentalRecord.getReservationsCount());

        var book = inventoryRecordRepository.getInventoryFrom(rentalRecord.getBookId());
        book.updateStateWith(rentalRecord);
        inventoryRecordRepository.saveInventoryRecord(book);
        bookAvailabilityMessagingOutPort.sendMessage(new BookAvailabilityMessage(book.getBookId(), book.getAvailableBooksCount()));
    }
}
