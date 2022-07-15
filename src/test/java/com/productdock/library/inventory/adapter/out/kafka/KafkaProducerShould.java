package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class KafkaProducerShould {

    private RecordProducer recordProducer = new RecordProducer();

    private final String topic = "test-book-availability";

    @Test
    void produceMessage() throws JsonProcessingException {
        var bookAvailabilityMessage = new BookAvailabilityChanged("1", 1);
        var producerRecord = recordProducer.createKafkaRecord(topic, bookAvailabilityMessage);
        String desiredValue = "{\"bookId\":\"" + bookAvailabilityMessage.getBookId() + "\",\"availableBookCount\":" + bookAvailabilityMessage.getAvailableBookCount() + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }
}
