package com.productdock.library.inventory.domain;

import org.junit.jupiter.api.Test;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionMother.bookSubscription;
import static org.assertj.core.api.Assertions.assertThat;

class BookSubscriptionsShould {

    @Test
    void getSubscription() {
        var subscription = bookSubscription();

        assertThat(subscription.getBookId()).isEqualTo("1");
        assertThat(subscription.getUserId()).isEqualTo("userEmail");
    }

}
