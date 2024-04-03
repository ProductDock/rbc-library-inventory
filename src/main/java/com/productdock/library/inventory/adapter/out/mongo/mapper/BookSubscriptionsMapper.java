package com.productdock.library.inventory.adapter.out.mongo.mapper;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionEntity;
import com.productdock.library.inventory.domain.BookSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookSubscriptionsMapper {

    BookSubscriptionEntity toEntity(BookSubscription source);

    BookSubscription toDomain(BookSubscriptionEntity source);

}
