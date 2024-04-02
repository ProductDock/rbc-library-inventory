package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionsPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscriptions;
import com.productdock.library.inventory.domain.exception.InventoryException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookSubscriptionService implements BookSubscriptionUseCase {

    private BookSubscriptionsPersistenceOutPort subscriptionsPersistenceOutPort;
    private GetAvailableBooksCountQuery getAvailableBooksCountQuery;

    @Override
    public void subscribeToBook(String bookId, String userId) {
        if (getAvailableBooksCountQuery.getAvailableBooksCount(bookId) == 0) {
            var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookId(bookId)
                    .orElseGet(() -> BookSubscriptions.builder().bookId(bookId).build());

            bookSubscriptions.subscribeUser(userId);
            subscriptionsPersistenceOutPort.save(bookSubscriptions);
        } else {
            throw new InventoryException("Cannot subscribe to available book!");
        }
    }

    @Override
    public void unsubscribeFromBook(String bookId, String userId) {
        var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookId(bookId);
        bookSubscriptions.ifPresent(subscriptions -> {
            subscriptions.unsubscribeUser(userId);
            subscriptionsPersistenceOutPort.save(subscriptions);
        });
    }

    @Override
    public boolean checkSubscription(String bookId, String userId) {
        var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookId(bookId);
        return bookSubscriptions.map(subscriptions -> subscriptions.isUserSubscribed(userId)).orElse(false);
    }
}
