package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.application.port.in.CheckAvailableBooksForSubscriptionsUseCase;
import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.messaging.BookSubscriptionsMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CheckAvailableForSubscriptionsService implements CheckAvailableBooksForSubscriptionsUseCase {

    private BookSubscriptionPersistenceOutPort subscriptionPersistenceOutPort;
    private GetAvailableBooksCountQuery getAvailableBooksCountQuery;
    private BookSubscriptionUseCase bookSubscriptionUseCase;
    private BookSubscriptionsMessagingOutPort subscriptionsMessagingOutPort;

    @Override
    public void checkAvailableBooks() {
        var allSubscriptions = subscriptionPersistenceOutPort.getAll();
        var subscriptionsByBook = allSubscriptions.stream()
                .collect(Collectors.groupingBy(BookSubscription::getBookId));

        for (var subscriptionsEntry : subscriptionsByBook.entrySet()) {
            if (getAvailableBooksCountQuery.getAvailableBooksCount(subscriptionsEntry.getKey()) > 0) {
                resolveSubscriptions(subscriptionsEntry.getValue());
            }
        }
    }

    @SneakyThrows
    private void resolveSubscriptions(List<BookSubscription> subscriptions) {
        for (var subscription : subscriptions) {
            log.debug("Deleting book subscription with bookId: {}, and userId: {}", subscription.getBookId(), subscription.getUserId());
            bookSubscriptionUseCase.deleteSubscription(subscription.getBookId(), subscription.getUserId());
            subscriptionsMessagingOutPort.sendMessage(subscription, bookDetails);
        }
    }

}
