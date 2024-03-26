package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionsPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BookSubscriptionService implements BookSubscriptionUseCase {

    private BookSubscriptionsPersistenceOutPort subscriptionsPersistenceOutPort;

    @Override
    public void subscribeToBook(String bookId, String userId) {
        var bookSubscriptions = subscriptionsPersistenceOutPort.findByBookId(bookId)
                .orElseGet(() -> BookSubscriptions.builder().bookId(bookId).subscribers(new ArrayList<>()).build());

        bookSubscriptions.subscribeUser(userId);
        subscriptionsPersistenceOutPort.save(bookSubscriptions);
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
        return bookSubscriptions.map(subscriptions -> subscriptions.checkSubscription(userId)).orElse(false);
    }
}
