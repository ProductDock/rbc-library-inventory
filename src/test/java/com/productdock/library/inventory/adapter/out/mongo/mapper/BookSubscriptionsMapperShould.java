package com.productdock.library.inventory.adapter.out.mongo.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionsMother.bookSubscriptions;
import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionsEntityMother.bookSubscriptionsEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookSubscriptionsMapperImpl.class})
class BookSubscriptionsMapperShould {

    @Autowired
    private BookSubscriptionsMapper bookSubscriptionsMapper;

    @Test
    void mapBookSubscriptionsToBookSubscriptionsEntity() {
        var bookSubscriptions = bookSubscriptions();

        var bookSubscriptionsEntity = bookSubscriptionsMapper.toEntity(bookSubscriptions);

        assertThat(bookSubscriptionsEntity.getBookId()).isEqualTo(bookSubscriptions.getBookId());
        assertThat(bookSubscriptionsEntity.getSubscriberUserIds()).isEqualTo(bookSubscriptions.getSubscriberUserIds());
    }

    @Test
    void mapBookSubscriptionsEntityToBookSubscriptions() {
        var bookSubscriptionsEntity = bookSubscriptionsEntity();

        var bookSubscriptions = bookSubscriptionsMapper.toDomain(bookSubscriptionsEntity);

        assertThat(bookSubscriptions.getBookId()).isEqualTo(bookSubscriptionsEntity.getBookId());
        assertThat(bookSubscriptions.getSubscriberUserIds()).isEqualTo(bookSubscriptionsEntity.getSubscriberUserIds());
    }
}
