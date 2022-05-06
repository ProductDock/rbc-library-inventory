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
}
