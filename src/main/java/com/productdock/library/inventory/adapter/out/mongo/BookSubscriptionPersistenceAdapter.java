package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.mapper.BookSubscriptionEntityMapper;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class BookSubscriptionPersistenceAdapter implements BookSubscriptionPersistenceOutPort {

    private BookSubscriptionRepository subscriptionsRepository;
    private BookSubscriptionEntityMapper subscriptionMapper;

    @Override
    public void save(BookSubscription subscriptions) {
        subscriptionsRepository.save(subscriptionMapper.toEntity(subscriptions));
    }

    @Override
    public Optional<BookSubscription> findByBookIdAndUserId(String bookId, String userId) {
        return subscriptionsRepository.findByBookIdAndUserId(bookId, userId).map(subscriptionsEntity -> subscriptionMapper.toDomain(subscriptionsEntity));
    }

    @Override
    public void delete(BookSubscription subscriptions) {
        subscriptionsRepository.deleteByBookIdAndUserId(subscriptions.getBookId(), subscriptions.getUserId());
    }
}
