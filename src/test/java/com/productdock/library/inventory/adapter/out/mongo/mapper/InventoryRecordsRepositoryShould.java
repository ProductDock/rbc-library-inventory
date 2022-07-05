package com.productdock.library.inventory.adapter.out.mongo.mapper;

import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordEntityRepository;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordsRepository;
import com.productdock.library.inventory.adapter.out.mongo.entity.InventoryRecordEntity;
import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.exception.InventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryRecordsRepositoryShould {

    private static final String BOOK_ID = "1";
    private static final Optional<InventoryRecordEntity> INVENTORY_RECORD_ENTITY = Optional.of(mock(InventoryRecordEntity.class));
    private static final Inventory INVENTORY = mock(Inventory.class);

    @InjectMocks
    private InventoryRecordsRepository inventoryRecordsRepository;

    @Mock
    private InventoryRecordEntityRepository inventoryRecordEntityRepository;

    @Mock
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void getInventoryRecordWhenIdExist() {
        given(inventoryRecordEntityRepository.findByBookId(BOOK_ID)).willReturn(INVENTORY_RECORD_ENTITY);
        given(inventoryRecordMapper.toDomain(INVENTORY_RECORD_ENTITY.get())).willReturn(INVENTORY);

        var inventory = inventoryRecordsRepository.getInventoryFrom(BOOK_ID);

        assertThat(inventory).isEqualTo(INVENTORY);
    }

    @Test
    void getInventoryRecordWhenIdNotExist() {
        given(inventoryRecordEntityRepository.findByBookId(BOOK_ID)).willReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryRecordsRepository.getInventoryFrom(BOOK_ID))
                .isInstanceOf(InventoryException.class);
    }

    @Test
    void saveInventoryRecord() {
        given(inventoryRecordEntityRepository.findByBookId(INVENTORY.getBookId())).willReturn(Optional.empty());
        given(inventoryRecordMapper.toEntity(INVENTORY)).willReturn(INVENTORY_RECORD_ENTITY.get());

        inventoryRecordsRepository.saveInventoryRecord(INVENTORY);

        verify(inventoryRecordEntityRepository).save(INVENTORY_RECORD_ENTITY.get());
    }
}
