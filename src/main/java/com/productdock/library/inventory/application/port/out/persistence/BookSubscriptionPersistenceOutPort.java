package com.productdock.library.inventory.application.port.out.persistence;

import com.productdock.library.inventory.domain.BookSubscription;

import java.util.List;
import java.util.Optional;

public interface BookSubscriptionPersistenceOutPort {

    void save(BookSubscription subscription);

    Optional<BookSubscription> findByBookIdAndUserId(String bookId, String userId);

    void delete(BookSubscription subscription);

    List<BookSubscription> getAll();
}
