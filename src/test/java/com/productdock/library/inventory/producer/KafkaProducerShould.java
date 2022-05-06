package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.book.BookAvailabilityMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class KafkaProducerShould {

    @InjectMocks
    private RecordProducer recordProducer;

    private final String topic = "test-book-availability";

    @Test
    void produceCorrectMessage() throws JsonProcessingException {
        var bookAvailabilityMessage = new BookAvailabilityMessage("1", 1);
        var producerRecord = recordProducer.createKafkaRecord(topic, bookAvailabilityMessage);
        String desiredValue = "{\"bookId\":\"" + bookAvailabilityMessage.getBookId() + "\",\"availableBookCount\":" + bookAvailabilityMessage.getAvailableBookCount() + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }

    @Test
    void throwExceptionWhenProducingBadEntity() {
        BookAvailabilityMessage bookAvailabilityMessage = mock(BookAvailabilityMessage.class);
        assertThrows(JsonProcessingException.class, () -> {
            var producerRecord = recordProducer.createKafkaRecord(topic, bookAvailabilityMessage);
        });
    }
}
