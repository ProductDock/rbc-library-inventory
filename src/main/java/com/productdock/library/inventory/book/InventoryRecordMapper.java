package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InventoryRecordMapper {

    InventoryRecordEntity toEntity(Inventory source);

    Inventory toDomain(InventoryRecordEntity source);
}
