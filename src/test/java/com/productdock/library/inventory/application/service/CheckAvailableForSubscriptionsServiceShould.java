package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.application.port.in.GetAvailableBooksCountQuery;
import com.productdock.library.inventory.application.port.out.messaging.BookSubscriptionsMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.BookSubscriptionPersistenceOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.productdock.library.inventory.data.provider.domain.BookSubscriptionMother.bookSubscription;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CheckAvailableForSubscriptionsServiceShould {

    public static final String BOOK_ID = "1";

    public static final String USER_ID = "userEmail";

    @InjectMocks
    private CheckAvailableForSubscriptionsService checkAvailableForSubscriptionsService;

    @Mock
    private BookSubscriptionPersistenceOutPort subscriptionsPersistenceOutPort;
    @Mock
    private GetAvailableBooksCountQuery getAvailableBooksCountQuery;
    @Mock
    private BookSubscriptionUseCase bookSubscriptionUseCase;
    @Mock
    private BookSubscriptionsMessagingOutPort subscriptionsMessagingOutPort;


    @Test
    public void checkAvailableBooks() {
        var subscription = getSubscription();
        given(subscriptionsPersistenceOutPort.getAll()).willReturn(subscription);
        given(getAvailableBooksCountQuery.getAvailableBooksCount(BOOK_ID)).willReturn(1);

        checkAvailableForSubscriptionsService.checkAvailableBooks();

        verify(bookSubscriptionUseCase).deleteSubscription(BOOK_ID, USER_ID);
    }

    @Test
    public void checkAvailableBooks_whenBookIsNotAvailable() {
        var subscription = getSubscription();
        given(subscriptionsPersistenceOutPort.getAll()).willReturn(subscription);
        given(getAvailableBooksCountQuery.getAvailableBooksCount(BOOK_ID)).willReturn(0);

        checkAvailableForSubscriptionsService.checkAvailableBooks();

        verifyNoInteractions(bookSubscriptionUseCase);
    }

    @Test
    public void checkAvailableBooks_whenThereAreNoSubscription() {
        var subscriptions = new ArrayList<BookSubscription>();
        given(subscriptionsPersistenceOutPort.getAll()).willReturn(subscriptions);

        checkAvailableForSubscriptionsService.checkAvailableBooks();

        verifyNoInteractions(bookSubscriptionUseCase);
    }

    private List<BookSubscription> getSubscription() {
        var subscriptions = new ArrayList<BookSubscription>();
        subscriptions.add(bookSubscription());
        return subscriptions;
    }

}
