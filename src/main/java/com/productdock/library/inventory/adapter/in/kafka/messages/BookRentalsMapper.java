package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.BookRentals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookRentalsMapper {

    @Mapping(target = "bookCopiesRentalState", source = "source.rentalRecords")
    BookRentals toDomain(BookRentalStatusChanged source);

}
