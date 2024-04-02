package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionsMother.bookSubscriptions;
import static org.assertj.core.api.Assertions.assertThat;

class BookSubscriptionsShould {

    public static final String USER_ID = "newUserEmail";

    @Test
    void subscribeUser() {
        var subscriptions = bookSubscriptions();

        subscriptions.subscribeUser(USER_ID);

        assertThat(subscriptions.getSubscriberUserIds()).hasSize(2);
    }

    @Test
    void unsubscribeUser() {
        var subscriptions = bookSubscriptions();
        subscriptions.subscribeUser(USER_ID);

        subscriptions.unsubscribeUser(USER_ID);

        assertThat(subscriptions.getSubscriberUserIds()).hasSize(1);
    }

    @Test
    void checkSubscription() {
        var subscriptions = bookSubscriptions();
        subscriptions.subscribeUser(USER_ID);

        assertThat(subscriptions.isUserSubscribed(USER_ID)).isTrue();
    }

}
