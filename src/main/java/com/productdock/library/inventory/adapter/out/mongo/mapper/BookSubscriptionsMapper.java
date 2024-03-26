package com.productdock.library.inventory.adapter.out.mongo.mapper;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionsEntity;
import com.productdock.library.inventory.domain.BookSubscriptions;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookSubscriptionsMapper {

    BookSubscriptionsEntity toEntity(BookSubscriptions source);

    BookSubscriptions toDomain(BookSubscriptionsEntity source);

}
