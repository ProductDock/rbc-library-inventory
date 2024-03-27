package com.productdock.library.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class BookSubscriptions {

    private String bookId;

    private List<String> subscribers;

    public void subscribeUser(String userId) {
        if (!subscribers.contains(userId)) {
            subscribers.add(userId);
        }
    }

    public void unsubscribeUser(String userId) {
        subscribers.remove(userId);
    }

    public boolean checkSubscription(String userId) {
        return subscribers.contains(userId);
    }

}
