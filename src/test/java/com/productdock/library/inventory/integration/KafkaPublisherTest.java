package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.kafka.KafkaPublisher;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import com.productdock.library.inventory.data.provider.domain.InventoryMother;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class KafkaPublisherTest extends KafkaTestBase {

    public static final String FIRST_BOOK = "1";
    public static final int AVAILABLE_BOOK_COUNT = 3;
    public static final String TEST_FILE = "testRecord.txt";

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @AfterEach
    @BeforeEach
    final void after() {
        File f = new File(TEST_FILE);
        f.delete();
    }

    @Test
    void shouldSendMessage() throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        kafkaPublisher.sendMessage(inventory());
        await()
                .atMost(Duration.ofSeconds(5))
                .until(ifFileExists(TEST_FILE));

        var bookAvailabilityChanged = getAvailableBookCountFromConsumersFile(TEST_FILE);

        assertThat(bookAvailabilityChanged.bookId).isEqualTo(FIRST_BOOK);
        assertThat(bookAvailabilityChanged.availableBookCount).isEqualTo(AVAILABLE_BOOK_COUNT);
    }

    private Callable<Boolean> ifFileExists(String testFile) {
        var checkForFile = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                File f = new File(testFile);
                return f.isFile();
            }
        };
        return checkForFile;
    }

    private BookAvailabilityChanged getAvailableBookCountFromConsumersFile(String testFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(testFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        var bookAvailabilityMessage = (BookAvailabilityChanged) objectInputStream.readObject();
        objectInputStream.close();
        return bookAvailabilityMessage;
    }
}
