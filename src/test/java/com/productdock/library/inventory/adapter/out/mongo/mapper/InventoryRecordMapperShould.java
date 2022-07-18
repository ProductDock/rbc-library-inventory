package com.productdock.library.inventory.adapter.out.mongo.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.domain.InventoryMother.inventory;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InventoryRecordMapperImpl.class})
class InventoryRecordMapperShould {

    @Autowired
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void mapInventoryToInventoryRecordEntity() {
        var inventory = inventory();

        var inventoryRecordEntity = inventoryRecordMapper.toEntity(inventory);

        assertThat(inventoryRecordEntity.getBookId()).isEqualTo(inventory.getBookId());
        assertThat(inventoryRecordEntity.getBookCopies()).isEqualTo(inventory.getBookCopies());
        assertThat(inventoryRecordEntity.getReservedBooks()).isEqualTo(inventory.getReservedBooks());
        assertThat(inventoryRecordEntity.getRentedBooks()).isEqualTo(inventory.getRentedBooks());
    }

    @Test
    void mapInventoryRecordEntityToInventory() {
        var inventoryRecordEntity = inventoryRecordEntity();

        var inventory = inventoryRecordMapper.toDomain(inventoryRecordEntity);

        assertThat(inventory.getBookId()).isEqualTo(inventoryRecordEntity.getBookId());
        assertThat(inventory.getBookCopies()).isEqualTo(inventoryRecordEntity.getBookCopies());
        assertThat(inventory.getReservedBooks()).isEqualTo(inventoryRecordEntity.getReservedBooks());
        assertThat(inventory.getRentedBooks()).isEqualTo(inventoryRecordEntity.getRentedBooks());
    }
}
