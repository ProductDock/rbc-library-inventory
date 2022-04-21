package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.productdock.library.inventory.producer.RecordProducer;
import com.productdock.library.inventory.record.RentalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerShould {
    @InjectMocks
    private RecordProducer recordProducer;

    private String topic = "test-rental-record-topic";

    @Test
    void produceCorrectMessage() throws JsonProcessingException {
        var rentalRecord = defaultRentalRecordBuilder().rents(Collections.emptyList()).reservations(Collections.emptyList()).build();
        var producerRecord = recordProducer.createKafkaRecord(topic, rentalRecord);
        Gson gson = new Gson();
        String desiredValue = "{\"bookId\":\"" + rentalRecord.getBookId() + "\",\"reservations\":" + gson.toJson(rentalRecord.getReservations()) +
                ",\"rents\":" + gson.toJson(rentalRecord.getRents()) + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }

    @Test
    void throwExceptionWhenProducingBadEntity() {
        RentalRecord rentalRecord = mock(RentalRecord.class);
        assertThrows(JsonProcessingException.class, () -> {
                var producerRecord = recordProducer.createKafkaRecord(topic, rentalRecord);
        });
    }
}
