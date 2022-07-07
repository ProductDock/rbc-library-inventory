package com.productdock.library.inventory.domain;

import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
import org.springframework.stereotype.Component;

@Component
public class RentalRecordMapper {

    public RentalRecord toDomain(RentalRecordMessage source) {
        return new RentalRecord(source.getBookId(), source.getRentalRecords());
    }

}
