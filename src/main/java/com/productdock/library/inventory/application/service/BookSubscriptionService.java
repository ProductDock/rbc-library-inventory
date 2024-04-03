package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import com.productdock.library.inventory.domain.exception.InventoryException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookSubscriptionService implements BookSubscriptionUseCase {

    private BookSubscriptionPersistenceOutPort subscriptionsPersistenceOutPort;
    private GetAvailableBooksCountQuery getAvailableBooksCountQuery;

    @Override
    public void subscribeToBook(String bookId, String userId) {
        if (getAvailableBooksCountQuery.getAvailableBooksCount(bookId) == 0) {
            var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookIdAndUserId(bookId, userId)
                    .orElseGet(() -> BookSubscription.builder().bookId(bookId).userId(userId).build());

            subscriptionsPersistenceOutPort.save(bookSubscriptions);
        } else {
            throw new InventoryException("Cannot subscribe to available book!");
        }
    }

    @Override
    public void unsubscribeFromBook(String bookId, String userId) {
        var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookIdAndUserId(bookId, userId);
        bookSubscriptions.ifPresent(subscriptions -> {
            subscriptionsPersistenceOutPort.delete(subscriptions);
        });
    }

    @Override
    public boolean checkSubscription(String bookId, String userId) {
        var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookIdAndUserId(bookId, userId);
        return bookSubscriptions.isPresent();
    }
}
