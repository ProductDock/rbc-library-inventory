package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.mapper.BookSubscriptionsMapper;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionsPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscriptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class BookSubscriptionsPersistenceAdapter implements BookSubscriptionsPersistenceOutPort {

    private BookSubscriptionsRepository subscriptionsRepository;
    private BookSubscriptionsMapper subscriptionsMapper;

    @Override
    public Optional<BookSubscriptions> findByBookId(String bookId) {
        return subscriptionsRepository.findByBookId(bookId).map(subscriptionsEntity -> subscriptionsMapper.toDomain(subscriptionsEntity));
    }

    @Override
    public void save(BookSubscriptions subscriptions) {
        var previousSubscriptionsEntity = subscriptionsRepository.findByBookId(subscriptions.getBookId());
        var newSubscriptionsEntity = subscriptionsMapper.toEntity(subscriptions);
        previousSubscriptionsEntity.ifPresent(subscriptionsEntity -> newSubscriptionsEntity.setId(previousSubscriptionsEntity.get().getId()));
        subscriptionsRepository.save(newSubscriptionsEntity);
    }

    @Override
    public void deleteByBookId(String bookId) {
        subscriptionsRepository.deleteByBookId(bookId);
    }
}
