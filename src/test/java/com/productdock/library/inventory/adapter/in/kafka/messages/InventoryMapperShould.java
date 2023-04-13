package com.productdock.library.inventory.adapter.in.kafka.messages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.in.kafka.InsertBookMessageMother.insertBookMessage;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InventoryMapperImpl.class})
class InventoryMapperShould {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Test
    void mapMessageToDomain() {
        var insertBookMessage = insertBookMessage();

        var inventory = inventoryMapper.toDomain(insertBookMessage);

        assertThat(inventory.getBookId()).isEqualTo(insertBookMessage.bookId);
        assertThat(inventory.getBookCopies()).isEqualTo(insertBookMessage.bookCopies);
        assertThat(inventory.getRentedBooks()).isZero();
        assertThat(inventory.getReservedBooks()).isZero();
    }

}
