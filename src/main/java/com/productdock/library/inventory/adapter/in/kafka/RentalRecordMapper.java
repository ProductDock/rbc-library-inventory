package com.productdock.library.inventory.adapter.in.kafka;

import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
import com.productdock.library.inventory.domain.RentalRecord;
import org.springframework.stereotype.Component;

@Component
public class RentalRecordMapper {

    public RentalRecord toDomain(RentalRecordMessage source) {
        return new RentalRecord(source.getBookId(), source.getRentalRecords());
    }

}
