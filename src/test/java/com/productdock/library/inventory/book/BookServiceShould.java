package com.productdock.library.inventory.book;

import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecord;
import kafka.coordinator.group.Empty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static com.productdock.library.inventory.data.provider.BookMother.defaultBook;
import static com.productdock.library.inventory.data.provider.BookMother.defaultBookBuilder;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceShould {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Publisher publisher;

    @BeforeEach
    final void before() {
        bookRepository.deleteAll();
    }

    @Test
    void saveNewBook() {
        var rentalRecord = defaultRentalRecord();
        var book = Optional.of(defaultBook());

        given(bookRepository.findById(rentalRecord.getBookId())).willReturn(book);

        bookService.saveBook(rentalRecord);

        verify(bookRepository).save(book.get());
    }

    @Test
    void triggerWarningWhenUnavailableBookReserved() {
        var rentalRecord = defaultRentalRecordBuilder().rents(Collections.emptyList()).build();
        var book = Optional.of(defaultBookBuilder().bookCopies(0).build());

        given(bookRepository.findById(rentalRecord.getBookId())).willReturn(book);

        bookService.saveBook(rentalRecord);

        verify(bookRepository).save(book.get());
        verify(publisher).sendMessage(rentalRecord);
    }

    @Test
    void triggerWarningWhenUnavailableBookRented() {
        var rentalRecord = defaultRentalRecordBuilder().reservations(Collections.emptyList()).build();
        var book = Optional.of(defaultBookBuilder().bookCopies(0).build());

        given(bookRepository.findById(rentalRecord.getBookId())).willReturn(book);

        bookService.saveBook(rentalRecord);

        verify(bookRepository).save(book.get());
        verify(publisher).sendMessage(rentalRecord);
    }
}
