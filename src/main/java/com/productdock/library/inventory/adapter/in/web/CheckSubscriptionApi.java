package com.productdock.library.inventory.adapter.in.web;

import com.productdock.library.inventory.adapter.in.web.mapper.BookSubscriptionMapper;
import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory/books")
@Slf4j
public record CheckSubscriptionApi(BookSubscriptionUseCase bookSubscriptionUseCase,
                                   BookSubscriptionMapper subscriptionMapper) {

    @GetMapping("/{bookId}/subscriptions")
    public BookSubscriptionDto getUserSubscription(@PathVariable("bookId") String bookId, @RequestParam("userId") String userId, Authentication authentication) {
        var subscription = bookSubscriptionUseCase.getSubscription(bookId, userId);
        return subscriptionMapper.toDto(subscription);
    }
}
