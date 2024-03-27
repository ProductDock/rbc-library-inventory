package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookSubscriptionsRepository extends MongoRepository<BookSubscriptionsEntity, String> {

    Optional<BookSubscriptionsEntity> findByBookId(String bookId);

    void deleteByBookId(String bookId);
}
