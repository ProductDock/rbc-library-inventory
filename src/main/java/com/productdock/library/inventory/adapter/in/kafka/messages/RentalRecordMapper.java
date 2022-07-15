package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.RentalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RentalRecordMapper {

    RentalRecord toDomain(BookRentalStatusChanged source);

}
