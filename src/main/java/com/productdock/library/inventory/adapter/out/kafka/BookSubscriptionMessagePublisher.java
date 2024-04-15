package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookSubscriptionMessage;
import com.productdock.library.inventory.application.port.out.messaging.BookSubscriptionsMessagingOutPort;
import com.productdock.library.inventory.domain.BookSubscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
class BookSubscriptionMessagePublisher implements BookSubscriptionsMessagingOutPort {

    @Value("${spring.kafka.topic.book-subscriptions}")
    private String kafkaTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RecordProducer recordProducer;

    public BookSubscriptionMessagePublisher(KafkaTemplate<String, String> kafkaTemplate, RecordProducer recordProducer) {
        this.kafkaTemplate = kafkaTemplate;
        this.recordProducer = recordProducer;
    }

    @Override
    public void sendMessage(BookSubscription subscription) throws ExecutionException, InterruptedException, JsonProcessingException {
        var bookSubscriptionMessage = new BookSubscriptionMessage(subscription.getBookId(), subscription.getUserId());
        log.warn("Sent kafka message: {} on kafka topic: {}", bookSubscriptionMessage, kafkaTopic);

        var kafkaRecord = recordProducer.createKafkaRecord(kafkaTopic, bookSubscriptionMessage);
        kafkaTemplate.send(kafkaRecord).get();
    }
}
