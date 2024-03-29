package com.productdock.library.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
        initSubscribers();
        if (!isUserSubscribed(userId)) {
            subscriberUserIds.add(userId);
        }
    }

    public void unsubscribeUser(String userId) {
        if (subscriberUserIds != null) {
            subscriberUserIds.remove(userId);
        }
    }

    public boolean isUserSubscribed(String userId) {
        if (subscriberUserIds != null) {
            return subscriberUserIds.contains(userId);
        }
        return false;
    }

    private void initSubscribers() {
        if (subscriberUserIds == null) {
            subscriberUserIds = new ArrayList<>();
        }
    }

}
