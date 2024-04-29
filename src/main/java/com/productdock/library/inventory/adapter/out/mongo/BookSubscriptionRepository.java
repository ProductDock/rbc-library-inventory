package com.productdock.library.inventory.adapter.out.mongo;

import com.productdock.library.inventory.adapter.out.mongo.entity.BookSubscriptionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookSubscriptionRepository extends MongoRepository<BookSubscriptionEntity, String> {

    Optional<BookSubscriptionEntity> findByBookId(String bookId);

    void deleteByBookId(String bookId);

    Optional<BookSubscriptionEntity> findByBookIdAndUserId(String bookId, String userId);

    void deleteByBookIdAndUserId(String bookId, String userId);
}
