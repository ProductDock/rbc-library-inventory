package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.InventoryRecordEntity;
import com.productdock.library.inventory.adapter.out.mongo.mapper.InventoryRecordMapper;
import com.productdock.library.inventory.domain.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryRecordPersistenceAdapterShould {

    private static final String BOOK_ID = "1";
    private static final Optional<InventoryRecordEntity> INVENTORY_RECORD_ENTITY = Optional.of(mock(InventoryRecordEntity.class));
    private static final Inventory INVENTORY = mock(Inventory.class);

    @InjectMocks
    private InventoryRecordPersistenceAdapter inventoryRecordPersistenceAdapter;

    @Mock
    private InventoryRecordRepository inventoryRecordRepository;

    @Mock
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void findInventoryByBookId() {
        given(inventoryRecordRepository.findByBookId(BOOK_ID)).willReturn(INVENTORY_RECORD_ENTITY);
        given(inventoryRecordMapper.toDomain(INVENTORY_RECORD_ENTITY.get())).willReturn(INVENTORY);

        var inventory = inventoryRecordPersistenceAdapter.findByBookId(BOOK_ID);

        assertThat(inventory).isEqualTo(Optional.of(INVENTORY));
    }

    @Test
    void saveInventory() {
        given(inventoryRecordRepository.findByBookId(INVENTORY.getBookId())).willReturn(Optional.empty());
        given(inventoryRecordMapper.toEntity(INVENTORY)).willReturn(INVENTORY_RECORD_ENTITY.get());

        inventoryRecordPersistenceAdapter.save(INVENTORY);

        verify(inventoryRecordRepository).save(INVENTORY_RECORD_ENTITY.get());
    }
}
