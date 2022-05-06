package com.productdock.library.inventory.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.productdock.library.inventory.data.provider.InventoryMother.*;
import static com.productdock.library.inventory.data.provider.InventoryRecordEntityMother.defaultInventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InventoryRecordMapperImpl.class})
class InventoryRecordMapperShould {

    @Autowired
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void mapInventoryToInventoryRecordEntity() {
        var inventory = defaultInventory();

        var inventoryRecordEntity = inventoryRecordMapper.toEntity(inventory);

        assertThat(inventoryRecordEntity.getBookId()).isEqualTo(inventory.getBookId());
    }

    @Test
    void mapInventoryRecordEntityToInventory() {
        var inventoryRecordEntity = defaultInventoryRecordEntity();

        var inventory = inventoryRecordMapper.toDomain(inventoryRecordEntity);

        assertThat(inventory.getBookId()).isEqualTo(inventoryRecordEntity.getBookId());
    }
}
