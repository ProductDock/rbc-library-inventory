package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.ActionMessage;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookSubscriptionMessage;
import com.productdock.library.inventory.application.port.out.messaging.BookSubscriptionsMessagingOutPort;
import com.productdock.library.inventory.domain.BookDetails;
import com.productdock.library.inventory.domain.BookSubscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
class BookSubscriptionMessagePublisher implements BookSubscriptionsMessagingOutPort {

    @Value("${spring.kafka.topic.notifications}")
    private String kafkaTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RecordProducer recordProducer;

    public BookSubscriptionMessagePublisher(KafkaTemplate<String, String> kafkaTemplate, RecordProducer recordProducer) {
        this.kafkaTemplate = kafkaTemplate;
        this.recordProducer = recordProducer;
    }

    @Override
    public void sendMessage(BookSubscription subscription, BookDetails bookDetails) throws ExecutionException, InterruptedException, JsonProcessingException {
        var bookNotificationMessage = generateBookNotificationMessage(subscription, bookDetails);
        log.debug("Sent kafka message: {} on kafka topic: {}", bookNotificationMessage, kafkaTopic);

        var kafkaRecord = recordProducer.createKafkaRecord(kafkaTopic, bookNotificationMessage);
        kafkaTemplate.send(kafkaRecord).get();
    }

    private BookSubscriptionMessage generateBookNotificationMessage(BookSubscription subscription, BookDetails bookDetails) {
        var title = "Book available!";
        var description = "Book: " + bookDetails.getTitle() + " is available again.";
        var action = ActionMessage.builder()
                .type("bookSubscription")
                .target(subscription.getBookId())
                .build();
        var message = BookSubscriptionMessage.builder()
                .title(title)
                .description(description)
                .userId(subscription.getUserId())
                .action(action)
                .build();

        return message;
    }
}
