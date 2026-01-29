package com.productdock.library.inventory.adapter.in.scheduled;

import com.productdock.library.inventory.application.port.in.CheckAvailableBooksForSubscriptionsUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CheckAvailableBookForSubscribersJob {

    private CheckAvailableBooksForSubscriptionsUseCase checkAvailableBooksForSubscriptionsUseCase;

    @Scheduled(cron = "${availability.auto-check.scheduled}")
    public void checkAvailablebooks() {
        log.debug("Started scheduled task for checking book availability");
        checkAvailableBooksForSubscriptionsUseCase.checkAvailableBooks();
    }

}
