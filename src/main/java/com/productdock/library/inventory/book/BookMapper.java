package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {

    BookEntity toEntity(Book source);

    Book toDomain(BookEntity source);
}
