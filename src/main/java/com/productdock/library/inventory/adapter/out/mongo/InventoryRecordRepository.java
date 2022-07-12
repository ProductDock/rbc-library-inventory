package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.InventoryRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryRecordRepository extends MongoRepository<InventoryRecordEntity, String> {

    Optional<InventoryRecordEntity> findByBookId(String bookId);
}

