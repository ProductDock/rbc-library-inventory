package com.productdock.library.inventory.record;

import com.productdock.library.inventory.domain.RentalRecord;
import org.springframework.stereotype.Component;

@Component
public class RentalRecordMapper {

    public RentalRecord toDomain(RentalRecordMessage source){
        return new RentalRecord(source.getBookId(), source.getRentalRecords());
    }

}
