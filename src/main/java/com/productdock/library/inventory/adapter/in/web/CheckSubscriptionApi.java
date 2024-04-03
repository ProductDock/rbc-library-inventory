package com.productdock.library.inventory.adapter.in.web;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import com.productdock.library.inventory.domain.BookSubscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory/books")
@Slf4j
public record CheckSubscriptionApi(BookSubscriptionUseCase bookSubscriptionUseCase) {

    @GetMapping("/{bookId}/subscriptions")
    public BookSubscription getSubscription(@PathVariable("bookId") String bookId, @RequestParam("userId") String userId, Authentication authentication) {
        return bookSubscriptionUseCase.getSubscription(bookId, userId);
    }
}
