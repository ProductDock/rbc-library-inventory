package com.productdock.library.inventory.application.port.in;

public interface BookSubscriptionUseCase {

    void subscribeToBook(String bookId, String userId);

    void unsubscribeFromBook(String bookId, String userId);

    boolean checkSubscription(String bookId, String userId);
}
