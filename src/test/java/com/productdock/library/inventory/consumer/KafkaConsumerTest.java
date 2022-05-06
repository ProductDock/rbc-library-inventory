package com.productdock.library.inventory.consumer;


import com.productdock.library.inventory.book.BookRepository;
import com.productdock.library.inventory.data.provider.KafkaTestBase;
import com.productdock.library.inventory.data.provider.KafkaTestProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class KafkaConsumerTest extends KafkaTestBase {

    @Autowired
    private KafkaTestProducer producer;

    @Autowired
    private BookRepository bookRepository;

    @Value("${spring.kafka.topic.rental-record}")
    private String topic;

    @BeforeEach
    final void before() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldSaveBook_whenMessageReceived() throws Exception{
        var rentalRecord = defaultRentalRecordMessage();
        System.out.println(rentalRecord.toString());
        producer.send(topic, rentalRecord);
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> bookRepository.findById("1").isPresent());
        assertThat(bookRepository.findById("1").get().getBookCopies()).isEqualTo(1);
        assertThat(bookRepository.findById("1").get().getRentedBooks()).isEqualTo(1);
        assertThat(bookRepository.findById("1").get().getReservedBooks()).isZero();
    }
}
