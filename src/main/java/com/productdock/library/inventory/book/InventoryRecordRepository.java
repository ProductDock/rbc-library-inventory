package com.productdock.library.inventory.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRecordRepository extends MongoRepository<InventoryRecordEntity, String> {

}
