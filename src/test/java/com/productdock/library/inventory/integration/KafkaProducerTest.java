package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.kafka.Publisher;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityMessage;
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
class KafkaProducerTest extends KafkaTestBase {

    public static final String FIRST_BOOK = "1";
    public static final int AVAILABLE_BOOK_COUNT = 3;
    public static final String TEST_FILE = "testRecord.txt";

    @Autowired
    private Publisher publisher;

    @AfterEach
    @BeforeEach
    final void after() {
        File f = new File(TEST_FILE);
        f.delete();
    }

    @Test
    void shouldSendMessage() throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        publisher.sendMessage(InventoryMother.inventory());
        await()
                .atMost(Duration.ofSeconds(5))
                .until(ifFileExists(TEST_FILE));

        var bookAvailabilityMessage = getAvailableBookCountFromConsumersFile(TEST_FILE);

        assertThat(bookAvailabilityMessage.getBookId()).isEqualTo(FIRST_BOOK);
        assertThat(bookAvailabilityMessage.getAvailableBookCount()).isEqualTo(AVAILABLE_BOOK_COUNT);
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

    private BookAvailabilityMessage getAvailableBookCountFromConsumersFile(String testFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(testFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        var bookAvailabilityMessage = (BookAvailabilityMessage) objectInputStream.readObject();
        objectInputStream.close();
        return bookAvailabilityMessage;
    }
}
