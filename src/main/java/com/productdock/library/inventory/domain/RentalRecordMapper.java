package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public class RentalRecordMapper {

    public RentalRecord toDomain(RentalRecordMessage source) {
        return new RentalRecord(source.getBookId(), source.getRentalRecords());
    }

}
