package com.productdock.library.inventory.application.port.out.persistence;

import com.productdock.library.inventory.domain.BookSubscriptions;

import java.util.Optional;

public interface BookSubscriptionsPersistenceOutPort {

    Optional<BookSubscriptions> findByBookId(String bookId);

    void save(BookSubscriptions subscriptions);

    void deleteByBookId(String bookId);
}
