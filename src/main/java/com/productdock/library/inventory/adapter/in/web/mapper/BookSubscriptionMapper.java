package com.productdock.library.inventory.adapter.in.web.mapper;

import com.productdock.library.inventory.adapter.in.web.BookSubscriptionDto;
import com.productdock.library.inventory.domain.BookSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookSubscriptionMapper {

    BookSubscriptionDto toDto(BookSubscription source);
}
