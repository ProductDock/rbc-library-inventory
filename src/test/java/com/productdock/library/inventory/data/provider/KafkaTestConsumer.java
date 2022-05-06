package com.productdock.library.inventory.data.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.book.BookAvailabilityMessage;
import com.productdock.library.inventory.record.RentalRecord;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
import com.productdock.library.inventory.record.RentalRecordMapper;
import com.productdock.library.inventory.record.RentalRecordMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


@Component
public class KafkaTestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTestConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.topic.book-availability}")
    public void receive(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        var bookAvailabilityMessage = objectMapper.readValue(consumerRecord.value(), BookAvailabilityMessage.class);
        writeRecordToFile(bookAvailabilityMessage);
    }

    private void writeRecordToFile(BookAvailabilityMessage rentalRecord) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("testRecord.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rentalRecord);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
