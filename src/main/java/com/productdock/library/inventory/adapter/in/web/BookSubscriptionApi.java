package com.productdock.library.inventory.adapter.in.web;

import com.productdock.library.inventory.application.port.in.BookSubscriptionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventory/subscriptions")
@Slf4j
public record BookSubscriptionApi(BookSubscriptionUseCase bookSubscriptionUseCase) {

    @PostMapping("/subscribe/{bookId}")
    public void subscribeToBook(@PathVariable("bookId") String bookId, Authentication authentication) {

        var userId = ((Jwt) authentication.getCredentials()).getClaim("email");
        log.debug("Subscribe request received for book id: {}, with user id: {}", bookId, userId.toString());
        bookSubscriptionUseCase.subscribeToBook(bookId, userId.toString());
    }

    @PostMapping("/unsubscribe/{bookId}")
    public void unsubscribeFromBook(@PathVariable("bookId") String bookId, Authentication authentication) {

        var userId = ((Jwt) authentication.getCredentials()).getClaim("email");
        log.debug("Unsubscribe request received for book id: {}, with user id: {}", bookId, userId.toString());
        bookSubscriptionUseCase.unsubscribeFromBook(bookId, userId.toString());
    }

    @GetMapping("/{bookId}")
    public boolean checkSubscription(@PathVariable("bookId") String bookId, Authentication authentication) {

        var userId = ((Jwt) authentication.getCredentials()).getClaim("email");
        log.debug("Check subscription request received for book id: {}, with user id: {}", bookId, userId.toString());
        return bookSubscriptionUseCase.checkSubscription(bookId, userId.toString());
    }
}
