package com.productdock.library.inventory.adapter.out.mongo.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionMother.bookSubscription;
import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionsEntityMother.bookSubscriptionEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookSubscriptionsMapperImpl.class})
class BookSubscriptionMapperShould {

    @Autowired
    private BookSubscriptionsMapper bookSubscriptionMapper;

    @Test
    void mapBookSubscriptionsToBookSubscriptionsEntity() {
        var bookSubscription = bookSubscription();

        var bookSubscriptionEntity = bookSubscriptionMapper.toEntity(bookSubscription);

        assertThat(bookSubscriptionEntity.getBookId()).isEqualTo(bookSubscription.getBookId());
        assertThat(bookSubscriptionEntity.getUserId()).isEqualTo(bookSubscription.getUserId());
    }

    @Test
    void mapBookSubscriptionsEntityToBookSubscriptions() {
        var bookSubscriptionEntity = bookSubscriptionEntity();

        var bookSubscription = bookSubscriptionMapper.toDomain(bookSubscriptionEntity);

        assertThat(bookSubscription.getBookId()).isEqualTo(bookSubscriptionEntity.getBookId());
        assertThat(bookSubscription.getUserId()).isEqualTo(bookSubscriptionEntity.getUserId());
    }
}
