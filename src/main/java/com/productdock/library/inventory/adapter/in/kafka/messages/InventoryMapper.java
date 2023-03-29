package com.productdock.library.inventory.adapter.in.kafka.messages;

import com.productdock.library.inventory.domain.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InventoryMapper {

    Inventory toDomain(InsertBookMessage source);

}
