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

    public static final String CLAIM_EMAIL = "email";

    @PostMapping("/subscribe/{bookId}")
    public void subscribeToBook(@PathVariable("bookId") String bookId, Authentication authentication) {

        var userId = getUserId(authentication);
        log.debug("Subscribe request received for book id: {}, with user id: {}", bookId, userId);
        bookSubscriptionUseCase.subscribeToBook(bookId, userId);
    }

    @DeleteMapping("/unsubscribe/{bookId}")
    public void unsubscribeFromBook(@PathVariable("bookId") String bookId, Authentication authentication) {

        var userId = getUserId(authentication);
        log.debug("Unsubscribe request received for book id: {}, with user id: {}", bookId, userId);
        bookSubscriptionUseCase.unsubscribeFromBook(bookId, userId);
    }

    private String getUserId(Authentication authentication) {
        return ((Jwt) authentication.getCredentials()).getClaim(CLAIM_EMAIL).toString();
    }
}
