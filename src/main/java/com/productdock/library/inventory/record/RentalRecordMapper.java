package com.productdock.library.inventory.record;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RentalRecordMapper {

    @Mapping(target = "rentalRecords", source = "source.rentalRecords")
    RentalRecord toDomain(RentalRecordMessage source);
}
