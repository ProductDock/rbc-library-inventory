package com.productdock.library.inventory.application.port.out.persistence;

import com.productdock.library.inventory.domain.BookSubscription;

import java.util.Optional;

public interface BookSubscriptionPersistenceOutPort {

    Optional<BookSubscription> findByBookId(String bookId);

    void save(BookSubscription subscriptions);

    void deleteByBookId(String bookId);

    Optional<BookSubscription> findByBookIdAndUserId(String bookId, String userId);

    void delete(BookSubscription subscriptions);
}
