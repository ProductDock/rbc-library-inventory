package com.productdock.library.inventory.data.provider;

import com.productdock.library.inventory.record.RentalRecord;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
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

    @Autowired
    private RentalRecordDeserializer rentalRecordDeserializer;

    @KafkaListener(topics = "${spring.kafka.topic.rental-record-warning-topic}")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        var rentalRecord = rentalRecordDeserializer.deserializeRentalRecord(consumerRecord);
        writeRecordToFile(rentalRecord);
    }

    private void writeRecordToFile(RentalRecord rentalRecord) {
        try {
            FileOutputStream  fileOutputStream = new FileOutputStream("testRecord.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rentalRecord);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
