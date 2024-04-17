package com.productdock.library.inventory.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookSubscriptionMessage;
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
    private static final String AVAILABILITY_FILE = "testRecord.txt";
    private static final String SUBSCRIPTION_FILE = "testSubscription.txt";


    @KafkaListener(topics = "${spring.kafka.topic.book-availability}")
    public void receive(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        var bookAvailabilityMessage = objectMapper.readValue(consumerRecord.value(), BookAvailabilityChanged.class);
        writeToFile(bookAvailabilityMessage, AVAILABILITY_FILE);
    }

    @KafkaListener(topics = "${spring.kafka.topic.book-subscriptions}")
    public void receiveSubscription(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        var bookSubscriptionMessage = objectMapper.readValue(consumerRecord.value(), BookSubscriptionMessage.class);
        writeToFile(bookSubscriptionMessage, SUBSCRIPTION_FILE);
    }

    private void writeToFile(Object message, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getMessage(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        var message = objectInputStream.readObject();
        objectInputStream.close();
        return message;
    }

    public static Callable<Boolean> ifFileExists(String fileName) {
        var checkForFile = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                File f = new File(fileName);
                return f.isFile();
            }
        };
        return checkForFile;
    }

    public static void clear(String fileName) {
        File f = new File(fileName);
        f.delete();
    }
}
