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

    private List<String> subscriberUserIds;

    public void subscribeUser(String userId) {
        if (!isUserSubscribed(userId)) {
            subscriberUserIds.add(userId);
        }
    }

    public void unsubscribeUser(String userId) {
        subscriberUserIds.remove(userId);
    }

    public boolean isUserSubscribed(String userId) {
        return subscriberUserIds.contains(userId);
    }

}
