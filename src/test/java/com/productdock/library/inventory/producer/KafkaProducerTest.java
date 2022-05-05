package com.productdock.library.inventory.producer;

import com.productdock.library.inventory.book.BookRepository;
import com.productdock.library.inventory.data.provider.KafkaTestBase;
import com.productdock.library.inventory.record.RentalRecord;
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

import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
public class KafkaProducerTest extends KafkaTestBase {

    public static final String FIRST_BOOK = "1";
    public static final String TEST_FILE = "testRecord.txt";

    @Autowired
    private Publisher publisher;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    final void before() {
        bookRepository.deleteAll();
    }

    @AfterEach
    final void after() {
        File f = new File(TEST_FILE);
        f.delete();
    }

    @Test
    void shouldSendMessage_whenWarningTriggered() throws IOException, ClassNotFoundException {
        publisher.sendMessage(defaultRentalRecord());
        Callable<Boolean> checkForFile = ifFileExists(TEST_FILE);
        await()
                .atMost(Duration.ofSeconds(20))
                .until(checkForFile);
        RentalRecord rentalRecord = getRentalRecordFromConsumersFile(TEST_FILE);
        assertThat(rentalRecord.getBookId().equals(FIRST_BOOK));
        assertThat(rentalRecord.getRents()).isNotNull();
    }

    private Callable<Boolean> ifFileExists(String testFile) {
        Callable<Boolean> checkForFile = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                File f = new File(testFile);
                return f.isFile();
            }
        };
        return checkForFile;
    }

    private RentalRecord getRentalRecordFromConsumersFile(String testFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(testFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        var rentalRecord = (RentalRecord) objectInputStream.readObject();
        objectInputStream.close();
        return rentalRecord;
    }
}
