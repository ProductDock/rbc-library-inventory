package com.productdock.library.inventory.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.productdock.library.inventory.data.provider.BookMother.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookMapperImpl.class})
class BookMapperShould {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void mapBookToBookEntity() {
        var book = defaultBook();

        var bookEntity = bookMapper.toEntity(book);

        assertThat(book.getBookId()).isEqualTo(bookEntity.getBookId());
    }

    @Test
    void mapBookEntityToBook() {
        var bookEntity = defaultBookEntity();

        var book = bookMapper.toDomain(bookEntity);

        assertThat(book.getBookId()).isEqualTo(bookEntity.getBookId());
    }

//    private void assertThatRecordsAreMatching (List<BookRentalRecord.BookCopy> bookCopies, List<BookInteraction> bookInteractions) {
//        assertThat(bookCopies).hasSameSizeAs(bookInteractions);
//        var bookCopy = bookCopies.get(0);
//        var bookInteraction = bookInteractions.get(0);
//        assertThatBookCopyIsMatching(bookCopy, bookInteraction);
//    }
//
//    private void assertThatBookCopyIsMatching(BookRentalRecord.BookCopy bookCopy, BookInteraction bookInteraction) {
//        assertThat(bookCopy.getPatron()).isEqualTo(bookInteraction.getUserEmail());
//        assertThat(bookCopy.getStatus()).isEqualTo(bookInteraction.getStatus());
//        assertThat(bookCopy.getDate()).isEqualTo(bookInteraction.getDate());
//    }

}
