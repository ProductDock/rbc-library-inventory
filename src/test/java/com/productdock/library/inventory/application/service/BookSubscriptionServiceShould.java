package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionsPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscriptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookSubscriptionServiceShould {

    private static final String BOOK_ID = "1";
    private static final String USER_ID = "1";

    private static final BookSubscriptions SUBSCRIPTIONS = new BookSubscriptions(BOOK_ID, new ArrayList<>());

    @InjectMocks
    private BookSubscriptionService subscriptionService;
    @Mock
    private BookSubscriptionsPersistenceOutPort subscriptionsPersistenceOutPort;

    @Test
    void subscribeUser() {
        SUBSCRIPTIONS.subscribeUser(USER_ID);

        subscriptionService.subscribeToBook(BOOK_ID, USER_ID);

        verify(subscriptionsPersistenceOutPort).save(SUBSCRIPTIONS);
    }

    @Test
    void unsubscribeUser() {
        given(subscriptionsPersistenceOutPort.findByBookId(BOOK_ID)).willReturn(Optional.of(SUBSCRIPTIONS));

        subscriptionService.unsubscribeFromBook(BOOK_ID, USER_ID);

        verify(subscriptionsPersistenceOutPort).save(SUBSCRIPTIONS);
    }
}
