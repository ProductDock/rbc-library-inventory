package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class KafkaProducerShould {

    private RecordProducer recordProducer = new RecordProducer();

    @Test
    void produceMessage() throws JsonProcessingException {
        var message = new BookAvailabilityChanged("1", 1);
        var producerRecord = recordProducer.createKafkaRecord("topic", message);
        String desiredValue = "{\"bookId\":\"" + message.bookId + "\",\"availableBookCount\":" + message.availableBookCount + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }
}
