package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import com.productdock.library.inventory.domain.exception.InventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionMother.bookSubscription;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookSubscriptionServiceShould {

    private static final String BOOK_ID = "1";
    private static final String USER_ID = "userEmail";

    private static final BookSubscription SUBSCRIPTION = bookSubscription();

    @InjectMocks
    private BookSubscriptionService subscriptionService;
    @Mock
    private BookSubscriptionPersistenceOutPort subscriptionPersistenceOutPort;
    @Mock
    private GetAvailableBooksCountQuery getAvailableBooksCountQuery;

    @Test
    void subscribeUser() {
        given(getAvailableBooksCountQuery.getAvailableBooksCount(BOOK_ID)).willReturn(0);

        subscriptionService.subscribeToBook(BOOK_ID, USER_ID);

        verify(subscriptionPersistenceOutPort).save(SUBSCRIPTION);
    }

    @Test
    void subscribeToAvailableBook() {
        given(getAvailableBooksCountQuery.getAvailableBooksCount(BOOK_ID)).willReturn(1);

        assertThatThrownBy(() -> subscriptionService.subscribeToBook(BOOK_ID, USER_ID))
                .isInstanceOf(InventoryException.class);
    }

    @Test
    void unsubscribeUser() {
        given(subscriptionPersistenceOutPort.findByBookIdAndUserId(BOOK_ID, USER_ID)).willReturn(Optional.of(SUBSCRIPTION));

        subscriptionService.unsubscribeFromBook(BOOK_ID, USER_ID);

        verify(subscriptionPersistenceOutPort).delete(SUBSCRIPTION);
    }
}
