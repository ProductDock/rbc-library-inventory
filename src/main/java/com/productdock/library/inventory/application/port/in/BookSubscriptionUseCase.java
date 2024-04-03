package com.productdock.library.inventory.application.port.in;

import com.productdock.library.inventory.domain.BookSubscription;

public interface BookSubscriptionUseCase {

    void subscribeToBook(String bookId, String userId);

    void unsubscribeFromBook(String bookId, String userId);

    boolean checkSubscription(String bookId, String userId);

    BookSubscription getSubscription(String bookId, String userId);
}
