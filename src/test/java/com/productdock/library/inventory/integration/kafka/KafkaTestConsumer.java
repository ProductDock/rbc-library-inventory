package com.productdock.library.inventory.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.Callable;


@Component
public record KafkaTestConsumer(ObjectMapper objectMapper) {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTestConsumer.class);
    private static final String FILE = "testRecord.txt";

    @KafkaListener(topics = "${spring.kafka.topic.book-availability}")
    public void receive(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        var bookAvailabilityMessage = objectMapper.readValue(consumerRecord.value(), BookAvailabilityChanged.class);
        writeRecordToFile(bookAvailabilityMessage);
    }

    private void writeRecordToFile(BookAvailabilityChanged message) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("testRecord.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BookAvailabilityChanged getMessage() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        var bookAvailabilityMessage = (BookAvailabilityChanged) objectInputStream.readObject();
        objectInputStream.close();
        return bookAvailabilityMessage;
    }

    public static Callable<Boolean> ifFileExists() {
        var checkForFile = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                File f = new File(FILE);
                return f.isFile();
            }
        };
        return checkForFile;
    }

    public static void clear() {
        File f = new File(FILE);
        f.delete();
    }
}
