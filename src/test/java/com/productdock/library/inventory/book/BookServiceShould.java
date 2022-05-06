package com.productdock.library.inventory.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecordMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static com.productdock.library.inventory.data.provider.BookMother.*;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordMessage;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceShould {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Publisher publisher;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private RentalRecordMapper rentalRecordMapper;

    @BeforeEach
    final void before() {
        bookRepository.deleteAll();
    }

    @Test
    void updateBookState_whenBookExists() throws Exception {
        var rentalRecordMessage = defaultRentalRecordMessage();
        var bookEntity = Optional.of(defaultBookEntity());
        var book = defaultBook();
        var rentalRecord = defaultRentalRecord();
        given(bookRepository.findById(rentalRecordMessage.getBookId())).willReturn(bookEntity);
        given(rentalRecordMapper.toDomain(rentalRecordMessage)).willReturn(rentalRecord);
        given(bookMapper.toDomain(bookEntity.get())).willReturn(book);
        given(bookMapper.toEntity(book)).willReturn(bookEntity.get());

        bookService.updateBookState(rentalRecordMapper.toDomain(rentalRecordMessage));

        verify(bookRepository).save(bookEntity.get());
    }

    @Test
    void updateBookState_whenBookDoesNotExist() throws Exception {
        var rentalRecordMessage = defaultRentalRecordMessage();
        var bookEntity = Optional.of(defaultBookEntityBuilder().rentedBooks(1).build());
        var rentalRecord = defaultRentalRecord();
        given(bookRepository.findById(rentalRecordMessage.getBookId())).willReturn(Optional.empty());
        given(rentalRecordMapper.toDomain(rentalRecordMessage)).willReturn(rentalRecord);

        bookService.updateBookState(rentalRecordMapper.toDomain(rentalRecordMessage));

        verify(bookRepository).save(bookEntity.get());
    }
}
